package com.wrg.employeedirectory.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wrg.employeedirectory.models.EmployeeResponseModel

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
@Dao
interface employeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(employee: EmployeeResponseModel) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllEmployees(employees : List<EmployeeResponseModel>)

    @Query("SELECT * FROM EmployeeResponseModel WHERE id = :id")
    fun getEmployee(id: String) : EmployeeResponseModel

    @Query("SELECT * FROM EmployeeResponseModel")
    fun getEmployees() : List<EmployeeResponseModel>

}