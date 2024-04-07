package com.example.androidstudyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidstudyapp.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {

    private val binding by lazy { FragmentFavouritesBinding.inflate(layoutInflater) }

    private val categoryName = null
    private val categoryImageUrl = null
    val categoryID = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val someInt = requireArguments().getInt(
            "ARG_CATEGORY_ID" to "$categoryId",
            "ARG_CATEGORY_NAME" to "$categoryName",
            "ARG_CATEGORY_IMAGE_URL" to "$categoryImageUrl")
    }
}