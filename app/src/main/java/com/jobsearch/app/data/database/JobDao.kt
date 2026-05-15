package com.jobsearch.app.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jobsearch.app.data.model.Job

@Dao
interface JobDao {

    @Query("SELECT * FROM jobs WHERE isSaved = 1 ORDER BY postedDate DESC")
    fun getSavedJobs(): LiveData<List<Job>>

    @Query("SELECT * FROM jobs WHERE id = :jobId")
    suspend fun getJobById(jobId: String): Job?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)

    @Delete
    suspend fun deleteJob(job: Job)

    @Query("DELETE FROM jobs WHERE id = :jobId")
    suspend fun deleteJobById(jobId: String)

    @Query("UPDATE jobs SET isSaved = :isSaved WHERE id = :jobId")
    suspend fun updateSavedStatus(jobId: String, isSaved: Boolean)

    @Query("SELECT EXISTS(SELECT 1 FROM jobs WHERE id = :jobId AND isSaved = 1)")
    suspend fun isJobSaved(jobId: String): Boolean

    @Query("SELECT * FROM jobs WHERE isSaved = 1")
    suspend fun getAllSavedJobsSync(): List<Job>
}
