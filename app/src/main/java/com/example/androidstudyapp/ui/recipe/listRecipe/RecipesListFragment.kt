package com.example.androidstudyapp.ui.recipe.listRecipe

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.ARG_CATEGORY_ID
import com.example.androidstudyapp.data.ARG_RECIPE_ID
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.databinding.FragmentRecipesListBinding
import com.example.androidstudyapp.model.STUB
import com.example.androidstudyapp.ui.recipe.RecipesListAdapter
import com.example.androidstudyapp.ui.recipe.recipe.RecipeFragment


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

        if (savedInstanceState == null)
            categoryId?.let { recipeListViewModel.loadListRecipe(it) }

        recipeListViewModel.state.observe(viewLifecycleOwner) {
            binding.tvCategory.text = categoryId?.let { getCategoryById(it)?.title }
            val ivListCategoryOfRecipe = binding.ivRecipe

            try {
                val ims = categoryId?.let {
                    getCategoryById(it)?.imageUrl?.let {
                        requireContext().assets.open(it)
                    }
                }
                val picture = Drawable.createFromStream(ims, null)
                ivListCategoryOfRecipe.setImageDrawable(picture)
            } catch (ex: Exception) {
                Log.e("mylog", "Error: $ex")
            }
            initRecyclerRecipe()
        }
    }

    private fun getBundleArg() {
        categoryId = arguments?.getInt(ARG_CATEGORY_ID)
    }

    private fun initRecyclerRecipe() {
        val adapter = RecipesListAdapter(STUB.getRecipesByCategoryId(categoryId))
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

        parentFragmentManager.commit {
            replace<RecipeFragment>(R.id.mainContainer, args = bundle)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun getCategoryById(id: Int): Category? {
        return STUB.getCategories().find { it.id == id }
    }
}
