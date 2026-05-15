package com.jobsearch.app.providers

import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobFilter

interface JobProvider {
    suspend fun searchJobs(filter: JobFilter): List<Job>
    fun getProviderName(): String
}
