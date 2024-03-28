package com.example.androidstudyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.ItemCategoryBinding


class CategoriesListAdapter(private val dataSet: List<Category>): RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCategoryBinding.inflate(LayoutInflater.from(itemView.context))
        val ivCategory1 = binding.ivCategory
        val tvTitleCategory1 = binding.tvTitleCategory
        val tvDescriptionCategory1 = binding.tvDescriptionCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val el = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(el)
    }

    override fun onBindViewHolder(holder: ViewHolder, numberOfPosition: Int) {
        val category: Category = dataSet[numberOfPosition]
        holder.tvTitleCategory1.text = category.title
        holder.tvDescriptionCategory1.text = category.description
    }

    override fun getItemCount() = dataSet.size
    }





