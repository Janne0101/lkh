package com.jobsearch.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.data.model.JobType

class Converters {
    @TypeConverter
    fun fromJobProvider(provider: JobProvider): String = provider.name

    @TypeConverter
    fun toJobProvider(name: String): JobProvider = JobProvider.valueOf(name)

    @TypeConverter
    fun fromJobType(type: JobType): String = type.name

    @TypeConverter
    fun toJobType(name: String): JobType = JobType.valueOf(name)
}

@Database(entities = [Job::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun jobDao(): JobDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "job_search_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
