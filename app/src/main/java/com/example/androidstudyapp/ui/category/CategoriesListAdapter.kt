package com.example.androidstudyapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.ImageUtils
import com.example.androidstudyapp.databinding.ItemCategoryBinding

class CategoriesListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(categoryId: Int)
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

        Glide.with(holder.ivCategoryHolder)
            .load(ImageUtils.getImageFullUrl(category.imageUrl))
            .error(R.drawable.img_error)
            .placeholder(R.drawable.img_placeholder)
            .into(holder.ivCategoryHolder)

        holder.itemView.setOnClickListener { itemClickListener?.onItemClick(category.id) }
    }

    override fun getItemCount() = dataSet.size
}
