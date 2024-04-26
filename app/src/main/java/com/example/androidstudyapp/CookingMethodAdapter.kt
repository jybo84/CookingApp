package com.example.androidstudyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.ItemCookingMethodBinding

class CookingMethodAdapter(private val dataSet: List<String>) :
    RecyclerView.Adapter<CookingMethodAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCookingMethodBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvDescriptionMethodCooking = binding.tvDescriptionMethodCooking
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCookingMethodBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = dataSet.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val methodCooking = dataSet[position]
//        holder.tvDescriptionMethodCooking.text = (position + 1).toString() + ". " + methodCooking
//       holder.tvDescriptionMethodCooking.text = "${position + 1}. $methodCooking"
       holder.tvDescriptionMethodCooking.text =
           holder.tvDescriptionMethodCooking.resources.getString(position + 1, methodCooking)
    }
}