package com.example.androidstudyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RecipeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    // TODO закоментировано специально, для проверки работоспособности приложения
//    fun openRecipeByRecipeId(id: Int) {
//        parentFragmentManager.commit {
//            replace(R.id.mainContainer)
//            setReorderingAllowed(true)
//            addToBackStack(null)
//        }
}


//Создать метод навигации openRecipeByRecipeId() на фрагмент с рецептом, принимающий параметр id рецепта,
//по которому был осуществлен клик.
//Реализовать навигацию на пустой RecipeFragment, при этом в метод replace() не передавать других
//параметров, кроме mainContainer.