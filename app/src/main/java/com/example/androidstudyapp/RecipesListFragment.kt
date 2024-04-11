package com.example.androidstudyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        categoryId = arguments?.getInt("ARG_CATEGORY_ID")
        categoryName = arguments?.getString("ARG_CATEGORY_NAME")
        categoryImageUrl = arguments?.getString("ARG_CATEGORY_IMAGE_URL")

        val adapter = RecipesListAdapter(STUB.getRecipesByCategoryId(categoryId))
        binding.rvRecipe.adapter = adapter
    }
// TODO закоментировал специально, а то он ошибку выдает (по условию не все параметры внутри)
//    fun openRecipeByRecipeId(id: Int){
//        parentFragmentManager.commit {
//            replace(R.id.mainContainer)
//            setReorderingAllowed(true)
//            addToBackStack(null)
//        }
//    }
    }

//Создать метод навигации openRecipeByRecipeId() на фрагмент с рецептом, принимающий параметр id рецепта,
//по которому был осуществлен клик.
//Реализовать навигацию на пустой RecipeFragment, при этом в метод replace() не передавать других
//параметров, кроме mainContainer.