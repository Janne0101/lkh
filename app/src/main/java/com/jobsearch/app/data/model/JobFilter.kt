package com.jobsearch.app.data.model

data class JobFilter(
    val keyword: String = "",
    val location: String = "",
    val selectedProviders: Set<JobProvider> = JobProvider.values().toSet(),
    val selectedJobTypes: Set<JobType> = emptySet(),
    val minSalary: Int? = null,
    val maxSalary: Int? = null,
    val remoteOnly: Boolean = false
)
