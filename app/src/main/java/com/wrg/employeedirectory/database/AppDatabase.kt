package com.wrg.employeedirectory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wrg.employeedirectory.models.EmployeeResponseModel

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
@Database(
    entities = [EmployeeResponseModel::class],
    version = 1
)
@TypeConverters(Employee.Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getemployeeDao(): employeeDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}