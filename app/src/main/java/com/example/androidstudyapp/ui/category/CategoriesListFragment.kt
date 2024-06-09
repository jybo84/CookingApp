package com.example.androidstudyapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.databinding.FragmentListCategoriesBinding
import com.example.androidstudyapp.model.STUB

class CategoriesListFragment : Fragment() {

    private val binding by lazy { FragmentListCategoriesBinding.inflate(layoutInflater) }
    private val categoriesListViewModel: CategoriesListViewModel by viewModels()

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
            categoriesListViewModel.loadCategoriesList()

        categoriesListViewModel.state.observe(viewLifecycleOwner) { state ->
            initRecycler(state.categories)
        }
    }

    private fun initRecycler(list: List<Category>) {
        val adapter = CategoriesListAdapter(list)
        binding.rvCategories.adapter = adapter
        adapter.setOnItemClickListener(object : CategoriesListAdapter.OnItemClickListener {
            override fun onItemClick(categoryId: Int) {
                openRecipesByCategoryId(categoryId)
            }
        })
    }

    fun openRecipesByCategoryId(categoryId: Int) {
        val category: Category =
            STUB.getCategories().find { it.id == categoryId } ?: throw IllegalArgumentException()

        findNavController().navigate(
            CategoriesListFragmentDirections.actionCategoriesListFragmentToRecipesListFragment(
                category
            )
        )
    }
}