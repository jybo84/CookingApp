package com.example.androidstudyapp.ui.recipe.listRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.ARG_CATEGORY_ID
import com.example.androidstudyapp.data.ARG_RECIPE_ID
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.databinding.FragmentRecipesListBinding
import com.example.androidstudyapp.ui.recipe.RecipesListAdapter


class RecipesListFragment : Fragment() {

    private val binding by lazy { FragmentRecipesListBinding.inflate(layoutInflater) }
    private var categoryId = arguments?.getInt(ARG_CATEGORY_ID)

    private val recipeListViewModel: RecipesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundleArg()

        categoryId?.let { recipeListViewModel.loadRecipes(it) }

        recipeListViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.apply {
                tvCategory.text = state.categoryName
                ivRecipe.setImageDrawable(state.categoryImage)
            }

            initRecyclerRecipe(state.recipes)
        }
    }

    private fun getBundleArg() {
        categoryId = arguments?.getInt(ARG_CATEGORY_ID)
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
        val bundle = bundleOf(
            ARG_RECIPE_ID to id,
        )
        findNavController().navigate(R.id.action_recipesListFragment_to_recipeFragment, bundle)
    }
}

