package com.example.androidstudyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.databinding.ActivityMainBinding
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController by lazy { findNavController(R.id.mainContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            buttonFavourites.setOnClickListener { navController.navigate(R.id.favouritesFragment) }

            buttonCategory.setOnClickListener { navController.navigate(R.id.categoriesListFragment) }
        }

        Log.i("MyLog", "Метод onCreate() выполняется на потоке: Main")

        val myThread = Thread {
            val url = URL("https://recipes.androidsprint.ru/api/category")
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.connect()

           val body: String = "responseBody: ${connection.inputStream.bufferedReader().readText()}"

            Log.i("MyLog", body)
            Log.i("MyLog", "Выполняю запрос в потоке myThread")
            Log.i("MyLog", "_________________________________")

            parseResponse(body)
        }

        myThread.start()
    }

    private fun parseResponse(response: String) {
        val responseObject = JSONObject(response)
        val item = Category(
            responseObject.getJSONObject("responseBody").getInt("id"),
            responseObject.getJSONObject("responseBody").getString("title"),
            responseObject.getJSONObject("responseBody").getString("description"),
            responseObject.getJSONObject("responseBody").getString("imageUrl"),
        )

        Log.i("MyLog", "id: ${item.id}")
        Log.i("MyLog", "title: ${item.title}")
        Log.i("MyLog", "description: ${item.description}")
        Log.i("MyLog", "imageUrl: ${item.imageUrl}")
    }
}
