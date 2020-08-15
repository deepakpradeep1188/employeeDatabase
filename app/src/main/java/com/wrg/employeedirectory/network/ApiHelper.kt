package com.wrg.employeedirectory.network

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
class ApiHelper(private val empApiService: EmployeeApiInterface) {

    suspend fun getUsers() = empApiService.getUsers()
}