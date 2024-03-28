package com.example.androidstudyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.androidstudyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    private val bindingRecyclerView by lazy { FragmentListCategoriesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CategoriesListFragment>(R.id.mainContainer)
            }
        }
        binding.buttonFavourites.setOnClickListener { changeFragment(FavouritesFragment()) }
        binding.buttonCategory.setOnClickListener { changeFragment(CategoriesListFragment()) }

//        val adapter = CategoriesListAdapter(STUB.getCategories())
//
//        val recyclerView = bindingRecyclerView.rvCategories
//
//        recyclerView.adapter = adapter

    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.mainContainer, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}

