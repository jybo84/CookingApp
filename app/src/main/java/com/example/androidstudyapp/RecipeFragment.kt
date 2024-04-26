package com.example.androidstudyapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.androidstudyapp.databinding.FragmentRecipeBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment() {

    private val binding by lazy { FragmentRecipeBinding.inflate(layoutInflater) }

    private val recipe by lazy { arguments?.parcelable<Recipe>(ARG_RECIPE) }

    private var recipeImageUrl: String? = null

    val adapterIngredient by lazy { recipe?.ingredients?.let { IngredientsAdapter(it) } }

    private var isFavourite = true


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

//        val listIngredients = recipe?.ingredients
//        val adapterIngredient = listIngredients?.let { IngredientsAdapter(it) }
        binding.rvIngredients.adapter = adapterIngredient
        binding.rvIngredients.addItemDecoration(makeDivider())

        val methodCook = recipe?.method
        val adapterCookingMethod = methodCook?.let { CookingMethodAdapter(it) }
        binding.rvMethod.adapter = adapterCookingMethod
        binding.rvMethod.addItemDecoration(makeDivider())

        val tvRecipeFragment = binding.tvRecipeInRecipeFragment
        tvRecipeFragment.text = recipe?.title.toString()

        getImageOfRecipe()

        makeSeekBar()

        makeFavouriteHeard()


        binding.ibHeartFavourites.setOnClickListener {
            isFavourite = !isFavourite
            makeFavouriteHeard()

        }
    }

    private fun getImageOfRecipe() {
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

    private fun makeDivider(): MaterialDividerItemDecoration {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        this.context?.let { divider.setDividerColorResource(it, R.color.color_divider) }
        divider.dividerInsetStart = 28
        divider.dividerInsetEnd = 28
        divider.isLastItemDecorated = false
        return divider
    }

    private fun makeSeekBar() {
        binding.sbNumberOfPortions.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.quantityPortions.text = progress.toString()

                adapterIngredient?.updateIngredients(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun makeFavouriteHeard() {
        if (isFavourite)
            binding.ibHeartFavourites.setImageResource(R.drawable.ic_heart_full)
        else {
            !isFavourite
            binding.ibHeartFavourites.setImageResource(R.drawable.ic_heart_empty)
        }
    }
}
