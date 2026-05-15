package com.wohnungssuche.app.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.wohnungssuche.app.data.db.AppDatabase
import com.wohnungssuche.app.data.model.Apartment
import com.wohnungssuche.app.data.repository.ApartmentRepository
import kotlinx.coroutines.launch

class ApartmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ApartmentRepository
    val allApartments: LiveData<List<Apartment>>
    val comparingApartments: LiveData<List<Apartment>>

    private val _currentApartment = MutableLiveData<Apartment?>()
    val currentApartment: LiveData<Apartment?> = _currentApartment

    init {
        val dao = AppDatabase.getInstance(application).apartmentDao()
        repository = ApartmentRepository(dao)
        allApartments = repository.allApartments
        comparingApartments = repository.comparingApartments
    }

    fun insert(apartment: Apartment) = viewModelScope.launch {
        repository.insert(apartment)
    }

    fun update(apartment: Apartment) = viewModelScope.launch {
        repository.update(apartment)
    }

    fun delete(apartment: Apartment) = viewModelScope.launch {
        repository.delete(apartment)
    }

    fun setComparing(id: Long, comparing: Boolean) = viewModelScope.launch {
        repository.setComparing(id, comparing)
    }

    fun updateRating(id: Long, rating: Int) = viewModelScope.launch {
        repository.updateRating(id, rating)
    }

    fun loadApartment(id: Long) = viewModelScope.launch {
        _currentApartment.postValue(repository.getById(id))
    }
}
