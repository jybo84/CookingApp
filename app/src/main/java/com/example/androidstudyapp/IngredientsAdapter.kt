package com.example.androidstudyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.FragmentRecipeBinding
import com.example.androidstudyapp.databinding.ItemIngredientBinding

class IngredientsAdapter(val dataSet: List<Ingredient>): RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    class ViewHolder(binding: FragmentRecipeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}