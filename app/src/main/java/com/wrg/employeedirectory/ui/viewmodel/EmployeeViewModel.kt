package com.wrg.employeedirectory.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wrg.employeedirectory.models.EmployeeResponseModel
import com.wrg.employeedirectory.ui.repository.EmployeeRepository
import com.wrg.employeedirectory.utils.Coroutines
import com.wrg.employeedirectory.utils.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
class EmployeeViewModel (private val mainRepository: EmployeeRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun saveDataToDb(employees:List<EmployeeResponseModel>)
    {
        mainRepository.saveallEmployeesToDb(employees)
    }

    fun getUsersFromdB() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getAllEmployees()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}