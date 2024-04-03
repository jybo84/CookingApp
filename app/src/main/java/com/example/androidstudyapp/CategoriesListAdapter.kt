package com.example.androidstudyapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.ItemCategoryBinding

class CategoriesListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick() {

        }
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivCategoryHolder = binding.ivCategory
        val tvTitleCategoryHolder = binding.tvTitleCategory
        val tvDescriptionCategoryHolder = binding.tvDescriptionCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
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
                Log.e("Ошибка.", "Картинка не загрузилась. Не верный адрес")
                null
            }
        holder.ivCategoryHolder.setImageDrawable(drawable)

        holder.itemView.setOnClickListener { itemClickListener?.onItemClick() }
    }

    override fun getItemCount() = dataSet.size
}
