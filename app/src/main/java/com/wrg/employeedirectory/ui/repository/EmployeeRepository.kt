package com.wrg.employeedirectory.ui.repository

import androidx.lifecycle.LiveData
import com.wrg.employeedirectory.database.AppDatabase
import com.wrg.employeedirectory.models.EmployeeResponseModel
import com.wrg.employeedirectory.network.ApiHelper
import com.wrg.employeedirectory.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
class EmployeeRepository (private val apiHelper: ApiHelper,private val db: AppDatabase) {

    suspend fun getUsers() = apiHelper.getUsers()

    public fun saveallEmployeesToDb(employees: List<EmployeeResponseModel>) {
        Coroutines.io {
            db.getemployeeDao().saveAllEmployees(employees)
        }
    }

     suspend fun getAllEmployees(): List<EmployeeResponseModel> {
        return withContext(Dispatchers.IO) {
            db.getemployeeDao().getEmployees()
        }
    }
}