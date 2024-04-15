package com.example.androidstudyapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.androidstudyapp.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private val binding by lazy { FragmentRecipeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rec = arguments?.getParcelable<Recipe>(ARG_RECIPE)
//        val rec = arguments?.getParcelable<Recipe>(ARG_RECIPE, Recipe::class.java)

        val tvRecFragment = binding.tvRecipeInRecipeFragment
        tvRecFragment.text = rec.toString()
    }
}


//1. Инициализация сверху
//2. Красивый вид по итогу
//3.      val rec = arguments?.getParcelable<Recipe>(ARG_RECIPE) -работает
//        val rec = arguments?.getParcelable<Recipe>(ARG_RECIPE, Recipe::class.java) - не работает
//4. работа с ГИТ