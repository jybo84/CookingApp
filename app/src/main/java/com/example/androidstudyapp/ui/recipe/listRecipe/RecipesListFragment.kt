package com.example.androidstudyapp.ui.recipe.listRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.databinding.FragmentRecipesListBinding
import com.example.androidstudyapp.ui.recipe.RecipesListAdapter


class RecipesListFragment : Fragment() {

    private val binding by lazy { FragmentRecipesListBinding.inflate(layoutInflater) }

    private val recipeListViewModel: RecipesListViewModel by viewModels()
    private val args: RecipesListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeListViewModel.loadRecipes(args.recipeId)

        recipeListViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.apply {
                tvCategory.text = state.categoryName
                ivRecipe.setImageDrawable(state.categoryImage)
            }

            initRecyclerRecipe(state.recipes)
        }
    }

    private fun initRecyclerRecipe(list: List<Recipe>) {

        val adapter = RecipesListAdapter(list)
        binding.rvRecipe.adapter = adapter
        adapter.setOnClickListenerRecipe(object : RecipesListAdapter.OnItemClickListenerRecipe {
            override fun onItemClickRecipe(recipeId: Int) {
                openRecipeByRecipeId(recipeId)
            }
        })
    }

    fun openRecipeByRecipeId(id: Int) {
        val action = RecipesListFragmentDirections.actionRecipesListFragmentToRecipeFragment(recipeId = id)
        findNavController().navigate(action)
    }
}

