package com.example.androidstudyapp.ui.recipe.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.ARG_RECIPE
import com.example.androidstudyapp.data.ARG_RECIPE_IMAGE
import com.example.androidstudyapp.data.FAVORITE_PREFS_KEY
import com.example.androidstudyapp.data.FILE_COLLECTION_MY_ID
import com.example.androidstudyapp.databinding.FragmentFavouritesBinding
import com.example.androidstudyapp.model.STUB
import com.example.androidstudyapp.ui.recipe.RecipesListAdapter
import com.example.androidstudyapp.ui.recipe.recipe.RecipeFragment

class FavouritesFragment : Fragment() {

    private val binding by lazy { FragmentFavouritesBinding.inflate(layoutInflater) }
    private val sharedPrefs by lazy {
        requireActivity().getSharedPreferences(FILE_COLLECTION_MY_ID, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvEmptyFavouriteList.isVisible = getFavorites().isEmpty()
        initRecycleViewFavourites()
    }

    private fun initRecycleViewFavourites() {
        val adapter =
            RecipesListAdapter(STUB.getRecipesByIds(getFavorites().map { it.toInt() }.toSet()))
        binding.rvFavouriteRecipeList.adapter = adapter
        adapter.setOnClickListenerRecipe(object : RecipesListAdapter.OnItemClickListenerRecipe {
            override fun onItemClickRecipe(recipeId: Int) {

                openRecipeByRecipeId(recipeId)
            }
        })
    }

    fun openRecipeByRecipeId(id: Int) {
        parentFragmentManager.commit {
            val recipeImage = STUB.getRecipeById(id)?.imageUrl
            val recipe = STUB.getRecipeById(id)
            val bundle = bundleOf(
                ARG_RECIPE to recipe,
                ARG_RECIPE_IMAGE to recipeImage
            )
            replace(R.id.mainContainer, RecipeFragment::class.java, bundle)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun getFavorites(): Set<String> {
        val savedList: Set<String> =
            sharedPrefs.getStringSet(FAVORITE_PREFS_KEY, emptySet()) ?: emptySet()
        return HashSet(savedList)
    }
}