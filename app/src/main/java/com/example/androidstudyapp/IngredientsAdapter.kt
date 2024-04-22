package com.example.androidstudyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.ItemIngredientBinding

class IngredientsAdapter(private val dataSet: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameOfIngredient = binding.nameOfIngredient
        val quantityIngredient = binding.quantityIngredients
        val unitOfMeasureIngredients = binding.unitOfMeasureIngredients
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient: Ingredient = dataSet[position]
        holder.nameOfIngredient.text = ingredient.description
        holder.quantityIngredient.text = ingredient.quantity
        holder.unitOfMeasureIngredients.text = ingredient.unitOfMeasure

    }
}