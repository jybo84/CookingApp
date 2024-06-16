package com.example.androidstudyapp.ui.recipe.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.databinding.FragmentFavouritesBinding
import com.example.androidstudyapp.ui.recipe.RecipesListAdapter

class FavouritesFragment : Fragment() {

    private val binding by lazy { FragmentFavouritesBinding.inflate(layoutInflater) }
    private val favouritesViewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
            favouritesViewModel.loadFavourites()

        favouritesViewModel.state.observe(viewLifecycleOwner) { state ->
            initRecycleViewFavourites(state.dataSet)
            binding.tvEmptyFavouriteList.isVisible = state.dataSet.isEmpty()
        }
    }

    private fun initRecycleViewFavourites(list: List<Recipe>) {
        val adapter =
            RecipesListAdapter(list)
        binding.rvFavouriteRecipeList.adapter = adapter
        adapter.setOnClickListenerRecipe(object : RecipesListAdapter.OnItemClickListenerRecipe {
            override fun onItemClickRecipe(recipeId: Int) {

                openRecipeByRecipeId(recipeId)
            }
        })
    }

    fun openRecipeByRecipeId(id: Int) {
        val action =
            FavouritesFragmentDirections.actionFavouritesFragmentToRecipeFragment(recipeId = id)
        findNavController().navigate(action)
    }
}