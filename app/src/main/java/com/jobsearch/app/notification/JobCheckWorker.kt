package com.jobsearch.app.notification

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.jobsearch.app.data.database.AppDatabase
import com.jobsearch.app.data.model.JobFilter
import com.jobsearch.app.data.repository.JobRepository
import java.util.concurrent.TimeUnit

class JobCheckWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val prefs = JobAlertPreferences(context)
        val alerts = prefs.getAlerts().filter { it.isActive }
        if (alerts.isEmpty()) return Result.success()

        val db = AppDatabase.getDatabase(context)
        val repository = JobRepository(db.jobDao())

        alerts.forEachIndexed { index, alert ->
            try {
                val filter = JobFilter(
                    keyword = alert.keyword,
                    location = alert.location,
                    remoteOnly = alert.remoteOnly
                )
                val jobs = repository.searchJobs(filter)
                val seenIds = prefs.getSeenJobIds(alert.id)

                val newJobs = if (seenIds.isEmpty()) {
                    // First run — mark all as seen without notifying
                    prefs.addSeenJobIds(alert.id, jobs.map { it.id }.toSet())
                    emptyList()
                } else {
                    jobs.filter { it.id !in seenIds }
                }

                if (newJobs.isNotEmpty()) {
                    prefs.addSeenJobIds(alert.id, newJobs.map { it.id }.toSet())
                    NotificationHelper.showNewJobsNotification(
                        context = context,
                        keyword = alert.keyword,
                        newJobs = newJobs,
                        notifId = index + 1000
                    )
                }
            } catch (e: Exception) {
                // Keep processing other alerts if one fails
            }
        }

        return Result.success()
    }

    companion object {
        const val WORK_NAME = "job_check_worker"

        fun schedule(context: Context, intervalHours: Long = 6) {
            NotificationHelper.createNotificationChannel(context)
            val request = PeriodicWorkRequestBuilder<JobCheckWorker>(
                intervalHours, TimeUnit.HOURS,
                15, TimeUnit.MINUTES  // flex interval
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }
    }
}
