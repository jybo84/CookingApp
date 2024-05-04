package com.example.androidstudyapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.androidstudyapp.databinding.FragmentFavouritesBinding

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
        start()
    }

    private fun initRecycleViewFavourites() {
        val adapter =
            RecipesListAdapter(STUB.getRecipesByIds(getFavorites().map { it.toInt() }.toSet()))
        binding.rvFavouriteRecipeList.adapter = adapter
        adapter.setOnClickListenerRecipe(object : RecipesListAdapter.OnItemClickListenerRecipe {
            override fun onItemClickRecipe(recipeId: Int) {
                openRecipesByCategoryId(recipeId)
            }
        })
    }

    private fun openRecipesByCategoryId(categoryId: Int) {
        parentFragmentManager.commit {
            val category = STUB.getCategories().find {
                it.id == categoryId
            } ?: return
            val categoryName = category.title
            val categoryImageUrl = category.imageUrl
            val bundle = bundleOf(
                ARG_CATEGORY_ID to categoryId,
                ARG_CATEGORY_NAME to categoryName,
                ARG_CATEGORY_IMAGE_URL to categoryImageUrl
            )
            val frag = RecipesListFragment()
            frag.arguments = bundle
            replace(R.id.mainContainer, frag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun getFavorites(): Set<String> {
        val savedList: Set<String> =
            sharedPrefs.getStringSet(FAVORITE_PREFS_KEY, emptySet()) ?: emptySet()
        return HashSet(savedList)
    }

    private fun start() {
        binding.tvEmptyFavouriteList.isVisible = getFavorites().isEmpty()
        initRecycleViewFavourites()
    }
}