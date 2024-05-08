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
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.ARG_CATEGORY_ID
import com.example.androidstudyapp.data.ARG_CATEGORY_IMAGE_URL
import com.example.androidstudyapp.data.ARG_CATEGORY_NAME
import com.example.androidstudyapp.data.ARG_RECIPE
import com.example.androidstudyapp.data.ARG_RECIPE_IMAGE
import com.example.androidstudyapp.databinding.FragmentRecipesListBinding
import com.example.androidstudyapp.model.STUB
import com.example.androidstudyapp.ui.recipe.RecipesListAdapter
import com.example.androidstudyapp.ui.recipe.recipe.RecipeFragment


class RecipesListFragment : Fragment() {

    private val binding by lazy { FragmentRecipesListBinding.inflate(layoutInflater) }
    private var categoryId: Int? = null
    private var categoryName: String? = null
    private var categoryImageUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundleArg()
        initRecyclerRecipe()

        binding.tvCategory.text = categoryName
        val ivListCategoryOfRecipe = binding.ivRecipe

        try {
            val ims = categoryImageUrl?.let { requireContext().assets.open(it) }
            val picture = Drawable.createFromStream(ims, null)
            ivListCategoryOfRecipe.setImageDrawable(picture)
        } catch (ex: Exception) {
            Log.e("mylog", "Error: $ex")
        }
    }

    private fun getBundleArg() {
        categoryId = arguments?.getInt(ARG_CATEGORY_ID)
        categoryName = arguments?.getString(ARG_CATEGORY_NAME)
        categoryImageUrl = arguments?.getString(ARG_CATEGORY_IMAGE_URL)
    }

    fun openRecipeByRecipeId(id: Int) {

        val recipeImage = STUB.getRecipeById(id)?.imageUrl
        val recipe = STUB.getRecipeById(id)
        val bundle = bundleOf(
            ARG_RECIPE to recipe,
            ARG_RECIPE_IMAGE to recipeImage
        )

        parentFragmentManager.commit {
            replace<RecipeFragment>(R.id.mainContainer, args = bundle)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
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
}