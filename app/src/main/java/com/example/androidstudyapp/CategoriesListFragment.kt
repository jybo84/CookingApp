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
            override fun onItemClick() {
                openRecipesByCategoryId(MainActivity(), RecipesListFragment())
            }
        })
    }

    fun openRecipesByCategoryId(activity: MainActivity, fragment: Fragment) {
        activity.supportFragmentManager.commit {
            replace(com.example.androidstudyapp.R.id.mainContainer, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}
