package com.example.androidstudyapp.ui.recipe.listRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.databinding.FragmentRecipesListBinding
import com.example.androidstudyapp.ui.recipe.RecipesListAdapter
import java.util.concurrent.Executors

class RecipesListFragment : Fragment() {

    private val binding by lazy { FragmentRecipesListBinding.inflate(layoutInflater) }

    private val recipeListViewModel: RecipesListViewModel by viewModels()
    private val args: RecipesListFragmentArgs by navArgs()
    private val threadPool = Executors.newFixedThreadPool(10)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeListViewModel.loadRecipes(args.categoryId)

        recipeListViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.apply {
                tvCategory.text = state.categoryName

                Glide.with(this@RecipesListFragment)
                    .load(state.categoryImageUrl)
                    .error(R.drawable.img_error)
                    .placeholder(R.drawable.img_placeholder)
                    .into(ivRecipe)
            }
            threadPool.execute() {
                if (state.recipes != null) {
                    initRecyclerRecipe(state.recipes)
                } else {
                    Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_LONG).show()
                }
            }
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

