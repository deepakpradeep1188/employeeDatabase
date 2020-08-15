package com.wrg.employeedirectory.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.wrg.employeedirectory.models.EmployeeResponseModel

/**
 * Created by Deepak Pradeep on 15/8/20.
 */

private const val EMPLOYEE_DETAILS_IN_DB = "employeedetailsindb"

class PreferenceProvider(
    context: Context
) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun setEmployeeDetailsToDbDone(isDone: Boolean) {
        preference.edit().putBoolean(
            EMPLOYEE_DETAILS_IN_DB,
            isDone
        ).apply()
    }

    fun isEmplyeeDetailsInDb():Boolean?
    {
        val isFoundinDb = preference.getBoolean(EMPLOYEE_DETAILS_IN_DB, false)
        return isFoundinDb
    }

    fun clearPreference()
    {
        preference.edit().clear().apply()
    }

}