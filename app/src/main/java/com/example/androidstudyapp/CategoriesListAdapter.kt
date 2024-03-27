package com.example.androidstudyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.ItemCategoryBinding

class CategoriesListAdapter(private val dataSet: Category) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val zzz = dataSet[position]
    }

    override fun getItemCount() = dataSet.size
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemCategoryBinding.inflate(LayoutInflater.from(itemView.context))
    val ivCategory = binding.ivCategory
    val titleCategory = binding.titleCategory
    val descriptionCategory = binding.descriptionCategory
}


