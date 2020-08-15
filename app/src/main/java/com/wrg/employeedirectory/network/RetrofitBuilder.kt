package com.wrg.employeedirectory.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
object RetrofitBuilder {

    private const val BASE_URL = "http://www.mocky.io/v2/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: EmployeeApiInterface = getRetrofit().create(EmployeeApiInterface::class.java)
}