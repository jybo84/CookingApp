package com.example.androidstudyapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.databinding.FragmentListCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesListFragment : Fragment() {

    private val binding by lazy { FragmentListCategoriesBinding.inflate(layoutInflater) }

    @delegate:Inject
    val categoriesListViewModel: CategoriesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val appContainer = (requireActivity().application as RecipesApplication).recipeModule
//        categoriesListViewModel = appContainer.categoriesListViewModelFactory.create()
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
            categoriesListViewModel.loadCategoriesList()

        categoriesListViewModel.state.observe(viewLifecycleOwner) { state ->
            if (state.categories != null) {
                initRecycler(state.categories)
            } else {
                Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_LONG).show()
            }
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
        findNavController().navigate(
            CategoriesListFragmentDirections.actionCategoriesListFragmentToRecipesListFragment(
                categoryId
            )
        )
    }
}