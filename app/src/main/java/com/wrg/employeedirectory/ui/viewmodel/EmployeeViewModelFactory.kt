package com.wrg.employeedirectory.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wrg.employeedirectory.database.AppDatabase
import com.wrg.employeedirectory.network.ApiHelper
import com.wrg.employeedirectory.ui.repository.EmployeeRepository

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
class EmployeeViewModelFactory (private val apiHelper: ApiHelper,private val appDb: AppDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            return EmployeeViewModel(EmployeeRepository(apiHelper,appDb)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}