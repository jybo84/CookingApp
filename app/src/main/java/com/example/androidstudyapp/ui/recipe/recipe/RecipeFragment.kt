package com.example.androidstudyapp.ui.recipe.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.ARG_RECIPE_ID
import com.example.androidstudyapp.databinding.FragmentRecipeBinding
import com.example.androidstudyapp.ui.category.CookingMethodAdapter
import com.example.androidstudyapp.ui.category.IngredientsAdapter
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment() {

    private val binding by lazy { FragmentRecipeBinding.inflate(layoutInflater) }
    private val recipeId by lazy { requireArguments().getInt(ARG_RECIPE_ID) }
    private var adapterIngredient: IngredientsAdapter? = null
    private var adapterCookingMethod: CookingMethodAdapter? = null
    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeViewModel.state.observe(viewLifecycleOwner) {
            Log.i("!!!", it.isFavourite.toString())
        }

        if (savedInstanceState == null)
            recipeViewModel.loadRecipe(recipeId)

        initUI()
    }

    private fun initUI() {

        binding.ibHeartFavourites.setOnClickListener {
            recipeViewModel.onFavoritesClicked()
        }

        recipeViewModel.state.observe(viewLifecycleOwner) { state ->

           adapterIngredient = state.recipe?.ingredients?.let { IngredientsAdapter(it) }
            binding.rvIngredients.adapter = adapterIngredient
            binding.rvIngredients.addItemDecoration(makeDivider())

            adapterCookingMethod = state.recipe?.method?.let { CookingMethodAdapter(it) }
            binding.rvMethod.adapter = adapterCookingMethod
            binding.rvMethod.addItemDecoration(makeDivider())

            val tvRecipeFragment = binding.tvRecipeInRecipeFragment
            tvRecipeFragment.text = state.recipe?.title.toString()

            binding.ivRecipe.setImageDrawable(state.recipeImage)

            makeSeekBar()

            makeFavouriteHeard(state.isFavourite)

        }
    }


    private fun makeDivider(): MaterialDividerItemDecoration {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        this.context?.let { divider.setDividerColorResource(it, R.color.color_divider) }
        val sizeInset = resources.getDimensionPixelSize(R.dimen.divider_inset)
        divider.dividerInsetStart = sizeInset
        divider.dividerInsetEnd = sizeInset
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

    private fun makeFavouriteHeard(isFavourite: Boolean) {
        if (isFavourite)
            binding.ibHeartFavourites.setImageResource(R.drawable.ic_heart_full)
        else
            binding.ibHeartFavourites.setImageResource(R.drawable.ic_heart_empty)
    }
}