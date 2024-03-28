package com.example.androidstudyapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.ItemCategoryBinding

class CategoriesListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCategoryBinding.inflate(LayoutInflater.from(itemView.context))
        val ivCategoryHolder = binding.ivCategory
        val tvTitleCategoryHolder = binding.tvTitleCategory
        val tvDescriptionCategoryHolder = binding.tvDescriptionCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, numberOfPosition: Int) {
        val category: Category = dataSet[numberOfPosition]
        holder.tvTitleCategoryHolder.text = category.title
        holder.tvDescriptionCategoryHolder.text = category.description

        val drawable =
            try {
                Drawable.createFromStream(
                    holder.itemView.context.assets.open(category.imageUrl),
                    null
                )
            } catch (e: Exception) {
                Log.d("Ошибка.", "Картинка не загрузлась. Не верный адрес")
                null
            }
        holder.ivCategoryHolder.setImageDrawable(drawable)
    }

    override fun getItemCount() = dataSet.size
}
