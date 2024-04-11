package com.example.androidstudyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.androidstudyapp.databinding.FragmentListCategoriesBinding


class CategoriesListFragment : Fragment() {

    private val binding by lazy { FragmentListCategoriesBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val adapter = CategoriesListAdapter(STUB.getCategories())
        binding.rvCategories.adapter = adapter
        adapter.setOnItemClickListener(object : CategoriesListAdapter.OnItemClickListener {
            override fun onItemClick(categoryId: Int) {
                openRecipesByCategoryId(categoryId)
            }
        })
    }

    fun openRecipesByCategoryId(categoryId: Int) {
        parentFragmentManager.commit {

            val category = STUB.getCategories().find {
                it.id == categoryId
            } ?: return

            val categoryName = category.title
            val categoryImageUrl = category.imageUrl
            val bundle = bundleOf(
                "ARG_CATEGORY_ID" to categoryId,
                "ARG_CATEGORY_NAME" to categoryName,
                "ARG_CATEGORY_IMAGE_URL" to categoryImageUrl
            )
            val frag = RecipesListFragment()
            frag.arguments = bundle
            replace(R.id.mainContainer, frag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}