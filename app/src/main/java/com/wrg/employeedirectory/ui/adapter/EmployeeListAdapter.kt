package com.wrg.employeedirectory.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wrg.employeedirectory.R
import com.wrg.employeedirectory.models.EmployeeResponseModel
import com.wrg.employeedirectory.ui.view.EmployeeDetailsActivity
import com.wrg.employeedirectory.ui.view.MainActivity
import kotlinx.android.synthetic.main.single_emp_layout.view.*

/**
 * Created by Deepak Pradeep on 15/8/20.
 */
class EmployeeListAdapter (private val employees: ArrayList<EmployeeResponseModel>) : RecyclerView.Adapter<EmployeeListAdapter.DataViewHolder>() {

    private lateinit var contxt: Context

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(employee: EmployeeResponseModel) {
            itemView.apply {
                textViewUserName.text = employee.name
                textViewUserEmail.text = employee.email
                Glide.with(imageViewAvatar.context)
                    .load(employee.profileImage)
                    .into(imageViewAvatar)

                itemView.setOnClickListener {
                    var intent:Intent = Intent(context,EmployeeDetailsActivity::class.java)
                    intent.putExtra("details",employee)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_emp_layout, parent, false))

    override fun getItemCount(): Int = employees.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(employees[position])
    }

    fun addUsers(users: List<EmployeeResponseModel>) {
        this.employees.apply {
            clear()
            addAll(users)
        }

    }

    fun setContext(context:Context) {
        contxt = context;
    }
}