package com.jobsearch.app.data.model

import java.io.Serializable

data class JobAlert(
    val id: String,
    val keyword: String,
    val location: String = "",
    val remoteOnly: Boolean = false,
    val intervalHours: Int = 6,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
) : Serializable
