package com.example.androidstudyapp.ui.recipe.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.databinding.FragmentFavouritesBinding
import com.example.androidstudyapp.ui.recipe.RecipesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private val binding by lazy { FragmentFavouritesBinding.inflate(layoutInflater) }

    @delegate:Inject
    val favouritesViewModel: FavouritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val appContainer = (requireActivity().application as RecipesApplication).recipeModule
//        favouritesViewModel = appContainer.favouriteModelFactory.create()
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

        if (savedInstanceState == null)
            favouritesViewModel.loadFavourites()

        favouritesViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.tvEmptyFavouriteList.isVisible = state.dataSet.isNullOrEmpty()

            if (state.dataSet != null) {
                initRecycleViewFavourites(state.dataSet)
            } else {
                Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_LONG).show()
            }
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