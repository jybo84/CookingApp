package com.example.androidstudyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.ItemIngredientBinding

class IngredientsAdapter(private val dataSet: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {


    class ViewHolder(binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameOfIngredient = binding.nameOfIngredient
        val quantityAndUnitOfMeasure = binding.quantityAndUnitOfMeasure
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
        holder.quantityAndUnitOfMeasure.text =
            holder.itemView.resources.getString(
                R.string.ingr_accaunt, ingredient.quantity, ingredient.unitOfMeasure
            )
    }
}


//class IngredientsAdapter(private val dataSet: List<Ingredient>): RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
//
//    class ViewHolder(private val binding: ItemIngredientBinding): RecyclerView.ViewHolder(binding.root){
//        fun bind(ing: Ingredient){
//            binding.nameOfIngredient.text = ing.description
//            binding.quantityAndUnitOfMeasure.text = itemView.resources.getString(R.string.ingr_accaunt, ing.quantity, ing.unitOfMeasure )
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent,false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataSet.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//    holder.bind(dataSet[position])
//    }
//}