package com.example.androidstudyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                openRecipesByCategoryId(categoryId, RecipesListFragment()  )
            }
        })
    }

    fun openRecipesByCategoryId(categoryId: Int, fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.mainContainer, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)

            val categoryName = R.id.tvCategory
            val categoryImageUrl = R.id.ivCategory

//            val bundle = bundleOf(
//                "ARG_CATEGORY_ID" to "$categoryId",
//                "ARG_CATEGORY_NAME" to "$categoryName",
//                "ARG_CATEGORY_IMAGE_URL" to "$categoryImageUrl"
//            )
        }
    }
}
