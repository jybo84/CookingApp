package com.example.androidstudyapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.R
import com.example.androidstudyapp.databinding.ItemCookingMethodBinding

class CookingMethodAdapter(var dataSet: List<String> = listOf()) :
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
        holder.tvDescriptionMethodCooking.text =
            holder.tvDescriptionMethodCooking.resources.getString(
                R.string.ingredient_account, position + 1, methodCooking
            )
    }
}