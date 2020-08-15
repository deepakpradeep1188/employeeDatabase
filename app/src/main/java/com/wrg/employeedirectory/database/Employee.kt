package com.wrg.employeedirectory.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wrg.employeedirectory.models.Address
import com.wrg.employeedirectory.models.Company

/**
 * Created by Deepak Pradeep on 15/8/20.
 */

@Entity
class Employee {
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = false)
    var id: String = ""
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("profile_image")
    @Expose
    var profileImage: String? = null
    @SerializedName("address")
    @Expose
    var address: Address? = null
    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("website")
    @Expose
    var website: String? = null
    @SerializedName("company")
    @Expose
    var company: Company? = null

    class Converters {
        @TypeConverter
        fun DataToJson(value: Company?) = Gson().toJson(value)

        @TypeConverter
        fun jsonTodata(value: String) = Gson().fromJson(value, Company::class.java)

        @TypeConverter
        fun DataToJson(value: Address?) = Gson().toJson(value)

        @TypeConverter
        fun jsonTodataa(value: String) = Gson().fromJson(value, Address::class.java)
    }

}