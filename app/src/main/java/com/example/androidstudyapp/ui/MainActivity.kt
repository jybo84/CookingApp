package com.example.androidstudyapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.androidstudyapp.R
import com.example.androidstudyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var navCont: NavController =
        (supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment).navController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navCont = findNavController(R.id.mainContainer)

        binding.apply {
            buttonFavourites.setOnClickListener {
                navCont.navigate(
                    R.id.favouritesFragment
                )
            }
            buttonCategory.setOnClickListener {
                navCont.navigate(
                    R.id.categoriesListFragment
                )
            }
        }
    }
}


