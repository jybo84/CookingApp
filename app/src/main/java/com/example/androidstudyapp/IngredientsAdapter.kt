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

    var quantity = 0

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

    fun updateIngredients(progress: Int) {
    }
}


//В IngredientsAdapter создать метод updateIngredients(), который будет принимать целочисленное значение progress.
//При изменении прогресса вызывать во фрагменте созданный метод и передавать значение progress.
//В IngredientsAdapter создать переменную quantity и обновлять ее значение при вызове updateIngredients(),
//использовать эту переменную для перемножения количества ингредиентов при байндинге элементов.

//Добавить проверку для перемноженного значения на целостность числа. Если полученное число не целое,
//отображать значение с точкой (с одним знаком после точки). В ином случае отображать только целое число.