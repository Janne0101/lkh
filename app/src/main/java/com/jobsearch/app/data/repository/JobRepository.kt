package com.jobsearch.app.data.repository

import androidx.lifecycle.LiveData
import com.jobsearch.app.data.database.JobDao
import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobFilter
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.providers.IndeedProvider
import com.jobsearch.app.providers.LinkedInProvider
import com.jobsearch.app.providers.StepStoneProvider
import com.jobsearch.app.providers.XingProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class JobRepository(private val jobDao: JobDao) {

    private val providers = mapOf(
        JobProvider.INDEED to IndeedProvider(),
        JobProvider.LINKEDIN to LinkedInProvider(),
        JobProvider.STEPSTONE to StepStoneProvider(),
        JobProvider.XING to XingProvider()
    )

    val savedJobs: LiveData<List<Job>> = jobDao.getSavedJobs()

    suspend fun searchJobs(filter: JobFilter): List<Job> = coroutineScope {
        val activeProviders = filter.selectedProviders.ifEmpty { JobProvider.values().toSet() }

        val deferredResults = activeProviders.map { providerEnum ->
            async {
                try {
                    providers[providerEnum]?.searchJobs(filter) ?: emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }

        val allResults = deferredResults.flatMap { it.await() }

        // Merge saved status from DB
        val savedIds = jobDao.getAllSavedJobsSync().map { it.id }.toSet()
        allResults.map { job ->
            job.copy(isSaved = job.id in savedIds)
        }.sortedByDescending { it.postedDate }
    }

    suspend fun saveJob(job: Job) {
        jobDao.insertJob(job.copy(isSaved = true))
    }

    suspend fun unsaveJob(jobId: String) {
        jobDao.deleteJobById(jobId)
    }

    suspend fun toggleSaveJob(job: Job): Boolean {
        val isSaved = jobDao.isJobSaved(job.id)
        return if (isSaved) {
            jobDao.deleteJobById(job.id)
            false
        } else {
            jobDao.insertJob(job.copy(isSaved = true))
            true
        }
    }

    suspend fun isJobSaved(jobId: String): Boolean = jobDao.isJobSaved(jobId)
}
