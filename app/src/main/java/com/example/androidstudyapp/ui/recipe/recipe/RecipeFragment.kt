package com.example.androidstudyapp.ui.recipe.recipe

import android.content.Context
import android.graphics.drawable.Drawable
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
import com.example.androidstudyapp.data.ARG_RECIPE
import com.example.androidstudyapp.data.ARG_RECIPE_IMAGE
import com.example.androidstudyapp.data.FAVORITE_PREFS_KEY
import com.example.androidstudyapp.data.FILE_COLLECTION_MY_ID
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.databinding.FragmentRecipeBinding
import com.example.androidstudyapp.ui.category.CookingMethodAdapter
import com.example.androidstudyapp.ui.category.IngredientsAdapter
import com.example.androidstudyapp.ui.parcelable
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment() {

    private val binding by lazy { FragmentRecipeBinding.inflate(layoutInflater) }
    private val recipe by lazy { arguments?.parcelable<Recipe>(ARG_RECIPE) }
    private var recipeImageUrl: String? = null
    val adapterIngredient by lazy { recipe?.ingredients?.let { IngredientsAdapter(it) } }
    private val adapterCookingMethod by lazy { recipe?.method?.let { CookingMethodAdapter(it) } }
    private val sharedPrefs by lazy {
        requireActivity().getSharedPreferences(FILE_COLLECTION_MY_ID, Context.MODE_PRIVATE)
    }

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

        viewModel.state.observe(viewLifecycleOwner) {
            Log.i("!!!", it.isFavourite.toString())
        }
    }

    private fun initUI() {
        binding.rvIngredients.adapter = adapterIngredient
        binding.rvIngredients.addItemDecoration(makeDivider())

        binding.rvMethod.adapter = adapterCookingMethod
        binding.rvMethod.addItemDecoration(makeDivider())

        val tvRecipeFragment = binding.tvRecipeInRecipeFragment
        tvRecipeFragment.text = recipe?.title.toString()

        getImageOfRecipe()

        makeSeekBar()

        makeFavouriteHeard()

        binding.ibHeartFavourites.setOnClickListener {

            val myListRecipes = getFavorites()
            if (getFavorites().contains(recipe?.id.toString()))
                myListRecipes.remove(recipe?.id.toString())
            else myListRecipes.add(recipe?.id.toString())

            saveFavorites(myListRecipes)

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

    private fun makeFavouriteHeard() {
        if (getFavorites().contains(recipe?.id.toString()))
            binding.ibHeartFavourites.setImageResource(R.drawable.ic_heart_full)
        else
            binding.ibHeartFavourites.setImageResource(R.drawable.ic_heart_empty)
    }

    private fun saveFavorites(listIdFavouritesRecipes: Set<String>) {
        with(sharedPrefs.edit()) {
            putStringSet(FAVORITE_PREFS_KEY, listIdFavouritesRecipes)
        }.apply()
    }

    private fun getFavorites(): MutableSet<String> {
        val savedList: Set<String> =
            sharedPrefs.getStringSet(FAVORITE_PREFS_KEY, emptySet()) ?: emptySet()
        return HashSet(savedList)
    }
}