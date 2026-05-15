package com.wohnungssuche.app.data.repository

import androidx.lifecycle.LiveData
import com.wohnungssuche.app.data.db.ApartmentDao
import com.wohnungssuche.app.data.model.Apartment

class ApartmentRepository(private val dao: ApartmentDao) {

    val allApartments: LiveData<List<Apartment>> = dao.getAllApartments()
    val comparingApartments: LiveData<List<Apartment>> = dao.getComparingApartments()

    suspend fun insert(apartment: Apartment): Long = dao.insert(apartment)

    suspend fun update(apartment: Apartment) = dao.update(apartment)

    suspend fun delete(apartment: Apartment) = dao.delete(apartment)

    suspend fun deleteById(id: Long) = dao.deleteById(id)

    suspend fun setComparing(id: Long, comparing: Boolean) = dao.setComparing(id, comparing)

    suspend fun updateRating(id: Long, rating: Int) = dao.updateRating(id, rating)

    suspend fun getById(id: Long): Apartment? = dao.getApartmentById(id)
}
