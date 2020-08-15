package com.wrg.employeedirectory.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wrg.employeedirectory.R
import com.wrg.employeedirectory.database.AppDatabase
import com.wrg.employeedirectory.models.EmployeeResponseModel
import com.wrg.employeedirectory.network.ApiHelper
import com.wrg.employeedirectory.network.RetrofitBuilder
import com.wrg.employeedirectory.ui.adapter.EmployeeListAdapter
import com.wrg.employeedirectory.ui.viewmodel.EmployeeViewModel
import com.wrg.employeedirectory.ui.viewmodel.EmployeeViewModelFactory
import com.wrg.employeedirectory.utils.PreferenceProvider
import com.wrg.employeedirectory.utils.Status
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var empViewModel: EmployeeViewModel
    private lateinit var adapter: EmployeeListAdapter
    private  lateinit var empoyees : List<EmployeeResponseModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
    }

    private fun setupViewModel() {
        empViewModel = ViewModelProviders.of(
            this,
            EmployeeViewModelFactory(ApiHelper(RetrofitBuilder.apiService),AppDatabase.invoke(this))
        ).get(EmployeeViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EmployeeListAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

        searchtext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable) { //after the change calling the method and passing the search input

               if(!empoyees.isEmpty()) {
                   filter(editable.toString(), empoyees)
               }

            }
        })
    }

    private fun setupObservers() {

        if(!PreferenceProvider(this).isEmplyeeDetailsInDb()!!) {
            empViewModel.getUsers().observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { users -> retrieveList(users) }
                        }
                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }
        else
        {
            empViewModel.getUsersFromdB().observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { users -> retrieveList(users) }
                        }
                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    private fun retrieveList(employeess: List<EmployeeResponseModel>) {
        searchtext.visibility = View.VISIBLE
        if(!PreferenceProvider(this).isEmplyeeDetailsInDb()!!) {
            empViewModel.saveDataToDb(employeess)
            PreferenceProvider(this).setEmployeeDetailsToDbDone(true)
        }
        empoyees = employeess
        adapter.apply {
            setContext(this@MainActivity)
            addUsers(employeess)
            notifyDataSetChanged()
        }
    }

    private fun filter(text: String,employeeList:List<EmployeeResponseModel>) { //new array list that will hold the filtered data
        val filterdNames: ArrayList<EmployeeResponseModel> = ArrayList()
        employeeList.forEach { emp ->
            if (emp.name.toLowerCase().contains(text.toLowerCase()) || emp.username.toLowerCase().contains(text.toLowerCase())) { //adding the element to filtered list
                filterdNames.add(emp)
            }
        }
        //calling a method of the adapter class and passing the filtered list
        adapter.apply {
            setContext(this@MainActivity)
            addUsers(filterdNames)
            notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        setupObservers()
        searchtext?.text?.clear()
    }
}
