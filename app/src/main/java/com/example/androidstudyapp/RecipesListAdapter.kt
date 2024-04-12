package com.example.androidstudyapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.ItemRecipeBinding

class RecipesListAdapter(private val dataSet: List<Recipe>) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    interface OnItemClickListenerRecipe {
        fun onItemClickRecipe(recipeId: Int)
    }

    private var itemClickListenerRecipe: OnItemClickListenerRecipe? = null

    fun setOnClickListenerRecipe(listenerRecipe: OnItemClickListenerRecipe) {
        itemClickListenerRecipe = listenerRecipe
    }

    class ViewHolder(binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivRecipeBurgerHolder = binding.ivRecipeBurger
        val tvTitleBurgerHolder = binding.tvTitleBurger
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: Recipe = dataSet[position]
        holder.tvTitleBurgerHolder.text = recipe.title

        val drawable =
            try {
                Drawable.createFromStream(
                    holder.itemView.context.assets.open(recipe.imageUrl),
                    null
                )
            } catch (e: Exception) {
                Log.e("Ошибка.", "Картинка не загрузилась. Не верный адрес")
                null
            }
        holder.ivRecipeBurgerHolder.setImageDrawable(drawable)

        holder.itemView.setOnClickListener { itemClickListenerRecipe?.onItemClickRecipe(recipe.id) }
    }

    override fun getItemCount() = dataSet.size
}

