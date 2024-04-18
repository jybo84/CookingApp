package com.example.androidstudyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidstudyapp.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private val binding by lazy { FragmentRecipeBinding.inflate(layoutInflater) }

    private val recipe by lazy { arguments?.parcelable<Recipe>(ARG_RECIPE) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val listIngredients = recipe?.ingredients
        val adapterIngredient = listIngredients?.let { IngredientsAdapter(it) }
        binding.rvIngredients.adapter = adapterIngredient

        val methodCook = recipe?.method
        val adapterCookingMethod = methodCook?.let { CookingMethodAdapter(it) }
        binding.rvMethod.adapter = adapterCookingMethod

        val tvRecFragment = binding.tvRecipeInRecipeFragment
        tvRecFragment.text = recipe?.title.toString()
    }
}

//        val layMan = LinearLayoutManager(requireContext())
//        binding.rvIngredients.layoutManager = layMan
//        binding.rvIngredients.addItemDecoration(DividerItemDecoration(binding.rvIngredients.context, layMan.orientation))