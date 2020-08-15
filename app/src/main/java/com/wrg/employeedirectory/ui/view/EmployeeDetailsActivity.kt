package com.wrg.employeedirectory.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wrg.employeedirectory.R
import com.wrg.employeedirectory.models.Address
import com.wrg.employeedirectory.models.Company
import com.wrg.employeedirectory.models.EmployeeResponseModel
import kotlinx.android.synthetic.main.activity_employee_details.*
import kotlinx.android.synthetic.main.employee_detail_layout.*
import kotlinx.android.synthetic.main.employee_detail_layout.employeeNameValue
import kotlinx.android.synthetic.main.single_emp_layout.view.*


class EmployeeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        val model: EmployeeResponseModel = intent.getSerializableExtra("details") as EmployeeResponseModel
        employeeNameValue.text = model.name
        userNameValue.text = model.username
        emailValue.text = model.email
        websiteValue.text = model.website
        phoneValue.text = model.phone
        websiteValue.text = model.website
        Glide.with(this).load(model.profileImage)
            .placeholder(R.drawable.ic_launcher_background).into(employeeImage)

        val addrss: Address = model.address
        addressValue.text = addrss.suite +"," + addrss.city + "," + addrss.street + "," +addrss.zipcode

        val compny : Company = model.company
        companyValue.text = compny.name + "," +compny.bs + "," + compny.catchPhrase
    }
}
