package com.example.androidstudyapp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private val binding by lazy { FragmentRecipeBinding.inflate(layoutInflater) }

    private val recipe by lazy { arguments?.parcelable<Recipe>(ARG_RECIPE) }

    private var recipeImageUrl: String? = null

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


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initUI() {

        val listIngredients = recipe?.ingredients
        val adapterIngredient = listIngredients?.let { IngredientsAdapter(it) }
        binding.rvIngredients.adapter = adapterIngredient
        val dividerItemIngredients = DividerItemDecoration(binding.rvIngredients.context, RecyclerView.VERTICAL)
        dividerItemIngredients.setDrawable(resources.getDrawable(R.drawable.shape_divider, null))
        binding.rvIngredients.addItemDecoration(dividerItemIngredients)

        val methodCook = recipe?.method
        val adapterCookingMethod = methodCook?.let { CookingMethodAdapter(it) }
        binding.rvMethod.adapter = adapterCookingMethod
        val dividerItemMethod = DividerItemDecoration(binding.rvMethod.context, RecyclerView.VERTICAL)
        dividerItemMethod.setDrawable(resources.getDrawable(R.drawable.shape_divider, null))
        binding.rvMethod.addItemDecoration(dividerItemMethod)

        val tvRecipeFragment = binding.tvRecipeInRecipeFragment
        tvRecipeFragment.text = recipe?.title.toString()

        getImageOfRecipe()
    }
    private fun getImageOfRecipe(){
        recipeImageUrl = arguments?.getString(ARG_RECIPE_IMAGE)
        val recipeImage = binding.ivRecipe

        try {
            val ims = recipeImageUrl?.let { requireContext().assets.open(it) }
            val picture = Drawable.createFromStream(ims, null)
            recipeImage.setImageDrawable(picture)
        } catch (ex: Exception) {
            Log.e("mylog", "Error: $ex")
        }
    }
}


//        val layMan = LinearLayoutManager(requireContext())
//        binding.rvIngredients.layoutManager = layMan
//        binding.rvIngredients.addItemDecoration(DividerItemDecoration(binding.rvIngredients.context, layMan.orientation))

