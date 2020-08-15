package com.wrg.employeedirectory.network

import com.wrg.employeedirectory.models.EmployeeResponseModel
import retrofit2.http.GET

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
interface EmployeeApiInterface {
    @GET("5d565297300000680030a986/")
    suspend fun getUsers(): List<EmployeeResponseModel>
}