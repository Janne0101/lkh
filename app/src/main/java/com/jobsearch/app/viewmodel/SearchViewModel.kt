package com.jobsearch.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jobsearch.app.data.database.AppDatabase
import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobFilter
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.data.model.JobType
import com.jobsearch.app.data.repository.JobRepository
import kotlinx.coroutines.Job as CoroutineJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class SearchState {
    object Idle : SearchState()
    object Loading : SearchState()
    data class Success(val jobs: List<Job>) : SearchState()
    data class Error(val message: String) : SearchState()
}

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JobRepository

    private val _searchState = MutableLiveData<SearchState>(SearchState.Idle)
    val searchState: LiveData<SearchState> = _searchState

    private val _currentFilter = MutableLiveData(JobFilter())
    val currentFilter: LiveData<JobFilter> = _currentFilter

    private var searchJob: CoroutineJob? = null

    init {
        val db = AppDatabase.getDatabase(application)
        repository = JobRepository(db.jobDao())
        // Load all jobs on start
        performSearch(JobFilter())
    }

    fun search(keyword: String, location: String) {
        val updatedFilter = _currentFilter.value?.copy(
            keyword = keyword.trim(),
            location = location.trim()
        ) ?: JobFilter(keyword = keyword.trim(), location = location.trim())
        _currentFilter.value = updatedFilter
        performSearch(updatedFilter)
    }

    fun toggleProvider(provider: JobProvider) {
        val current = _currentFilter.value ?: return
        val selectedProviders = current.selectedProviders.toMutableSet()
        if (provider in selectedProviders) {
            if (selectedProviders.size > 1) {
                selectedProviders.remove(provider)
            }
        } else {
            selectedProviders.add(provider)
        }
        val updatedFilter = current.copy(selectedProviders = selectedProviders)
        _currentFilter.value = updatedFilter
        performSearch(updatedFilter)
    }

    fun selectAllProviders() {
        val current = _currentFilter.value ?: return
        val updatedFilter = current.copy(selectedProviders = JobProvider.values().toSet())
        _currentFilter.value = updatedFilter
        performSearch(updatedFilter)
    }

    fun setJobTypeFilter(jobTypes: Set<JobType>) {
        val current = _currentFilter.value ?: return
        val updatedFilter = current.copy(selectedJobTypes = jobTypes)
        _currentFilter.value = updatedFilter
        performSearch(updatedFilter)
    }

    fun setRemoteOnly(remoteOnly: Boolean) {
        val current = _currentFilter.value ?: return
        val updatedFilter = current.copy(remoteOnly = remoteOnly)
        _currentFilter.value = updatedFilter
        performSearch(updatedFilter)
    }

    fun toggleSaveJob(job: Job) {
        viewModelScope.launch {
            try {
                val newSavedState = repository.toggleSaveJob(job)
                // Update the job in current results
                val currentState = _searchState.value
                if (currentState is SearchState.Success) {
                    val updatedJobs = currentState.jobs.map {
                        if (it.id == job.id) it.copy(isSaved = newSavedState) else it
                    }
                    _searchState.value = SearchState.Success(updatedJobs)
                }
            } catch (e: Exception) {
                // Silently ignore save errors
            }
        }
    }

    private fun performSearch(filter: JobFilter) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _searchState.value = SearchState.Loading
            try {
                delay(100) // Small debounce
                val jobs = repository.searchJobs(filter)
                _searchState.value = SearchState.Success(jobs)
            } catch (e: Exception) {
                _searchState.value = SearchState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
