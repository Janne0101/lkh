package com.jobsearch.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jobsearch.app.data.database.AppDatabase
import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.repository.JobRepository
import kotlinx.coroutines.launch

class SavedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JobRepository

    val savedJobs: LiveData<List<Job>>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = JobRepository(db.jobDao())
        savedJobs = repository.savedJobs
    }

    fun removeJob(job: Job) {
        viewModelScope.launch {
            repository.unsaveJob(job.id)
        }
    }

    fun toggleSaveJob(job: Job) {
        viewModelScope.launch {
            repository.toggleSaveJob(job)
        }
    }
}
