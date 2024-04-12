package com.example.androidstudyapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.androidstudyapp.databinding.FragmentRecipesListBinding


class RecipesListFragment : Fragment() {

    private val binding by lazy { FragmentRecipesListBinding.inflate(layoutInflater) }
    private var categoryId: Int? = null
    private var categoryName: String? = null
    private var categoryImageUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundleArg()
        initRecyclerRecipe()

        binding.tvCategory.text = categoryName
        val ivListCategoryOfRecipe = binding.ivRecipe

        try {
            // get input stream
            val ims = categoryImageUrl?.let { requireContext().assets.open(it) }
            // load image as Drawable
            val picture = Drawable.createFromStream(ims, null)
            ivListCategoryOfRecipe.setImageDrawable(picture)
        } catch (ex: Exception) {
            Log.e("mylog", "Error: $ex")
        }
    }

    private fun getBundleArg() {
        categoryId = arguments?.getInt("ARG_CATEGORY_ID")
        categoryName = arguments?.getString("ARG_CATEGORY_NAME")
        categoryImageUrl = arguments?.getString("ARG_CATEGORY_IMAGE_URL")
    }


    fun openRecipeByRecipeId(id: Int) {
        parentFragmentManager.commit {
            replace(R.id.mainContainer, RecipeFragment())
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
