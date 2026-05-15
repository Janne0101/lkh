package com.jobsearch.app.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jobsearch.app.R
import com.jobsearch.app.data.model.Job
import com.jobsearch.app.ui.MainActivity

object NotificationHelper {

    const val CHANNEL_ID = "job_alerts"
    private const val CHANNEL_NAME = "Job-Benachrichtigungen"
    private const val CHANNEL_DESC = "Benachrichtigungen über neue passende Stellen"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESC
                enableVibration(true)
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    fun showNewJobsNotification(context: Context, keyword: String, newJobs: List<Job>, notifId: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(MainActivity.EXTRA_SEARCH_KEYWORD, keyword)
        }
        val pendingIntent = PendingIntent.getActivity(
            context, notifId, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val title = when (newJobs.size) {
            1 -> "1 neuer Job für „$keyword""
            else -> "${newJobs.size} neue Jobs für „$keyword""
        }

        val inboxStyle = NotificationCompat.InboxStyle().setSummaryText("JobSearch")
        newJobs.take(5).forEach { job ->
            inboxStyle.addLine("${job.title} · ${job.company}")
        }
        if (newJobs.size > 5) {
            inboxStyle.addLine("+ ${newJobs.size - 5} weitere Jobs…")
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_work)
            .setContentTitle(title)
            .setContentText(newJobs.first().let { "${it.title} · ${it.company}" })
            .setStyle(inboxStyle)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        try {
            NotificationManagerCompat.from(context).notify(notifId, notification)
        } catch (e: SecurityException) {
            // Permission not granted — user will see in-app prompt
        }
    }
}
