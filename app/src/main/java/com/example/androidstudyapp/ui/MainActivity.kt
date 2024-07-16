package com.example.androidstudyapp.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.androidstudyapp.R
import com.example.androidstudyapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController by lazy { findNavController(R.id.mainContainer) }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            buttonFavourites.setOnClickListener {
                navController.navigate(
                    R.id.favouritesFragment, null,
                    NavOptions.Builder()
                        .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                        .build()
                )
            }

            buttonCategory.setOnClickListener {
                navController.navigate(
                    R.id.categoriesListFragment, null,
                    NavOptions.Builder()
                        .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                        .build()
                )
            }
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                getColor(R.color.white), getColor(R.color.black)
            ),
            navigationBarStyle = SystemBarStyle.light(
                getColor(R.color.white),
                getColor(R.color.black)
            )
        )
    }
}
