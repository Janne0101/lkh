package com.jobsearch.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

enum class JobProvider(val displayName: String, val colorHex: String) {
    INDEED("Indeed", "#2557A7"),
    LINKEDIN("LinkedIn", "#0A66C2"),
    STEPSTONE("StepStone", "#FF6600"),
    XING("Xing", "#026466")
}

enum class JobType(val displayName: String) {
    FULL_TIME("Vollzeit"),
    PART_TIME("Teilzeit"),
    REMOTE("Remote"),
    HYBRID("Hybrid"),
    FREELANCE("Freelance")
}

@Entity(tableName = "jobs")
data class Job(
    @PrimaryKey
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val salary: String?,
    val jobType: JobType,
    val description: String,
    val provider: JobProvider,
    val url: String,
    val postedDate: String,
    val isRemote: Boolean,
    var isSaved: Boolean = false
) : Serializable
