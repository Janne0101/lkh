package com.wohnungssuche.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wohnungssuche.app.data.model.Apartment

@Dao
interface ApartmentDao {

    @Query("SELECT * FROM apartments ORDER BY rating DESC, createdAt DESC")
    fun getAllApartments(): LiveData<List<Apartment>>

    @Query("SELECT * FROM apartments WHERE id = :id")
    suspend fun getApartmentById(id: Long): Apartment?

    @Query("SELECT * FROM apartments WHERE isComparing = 1 ORDER BY rating DESC")
    fun getComparingApartments(): LiveData<List<Apartment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apartment: Apartment): Long

    @Update
    suspend fun update(apartment: Apartment)

    @Delete
    suspend fun delete(apartment: Apartment)

    @Query("DELETE FROM apartments WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE apartments SET isComparing = :comparing WHERE id = :id")
    suspend fun setComparing(id: Long, comparing: Boolean)

    @Query("UPDATE apartments SET rating = :rating WHERE id = :id")
    suspend fun updateRating(id: Long, rating: Int)
}
