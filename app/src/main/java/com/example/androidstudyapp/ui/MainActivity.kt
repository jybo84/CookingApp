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
import org.json.JSONArray
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

           val body: String =  connection.inputStream.bufferedReader().readText()

            Log.i("MyLog", "responseBody: $body")
            Log.i("MyLog", "Выполняю запрос в потоке myThread")
            Log.i("MyLog", "_________________________________")

            parseResponse(body)
        }
       myThread.start()
    }

    private fun parseResponse(response: String) {
        val responseObject = JSONArray(response)
        for (el in 0..responseObject.length()) {
            val item = Category(
                responseObject.getJSONObject(el).getInt("id"),
                responseObject.getJSONObject(el).getString("title"),
                responseObject.getJSONObject(el).getString("description"),
                responseObject.getJSONObject(el).getString("imageUrl"),
            )

            Log.i("MyLog", "id: ${item.id}")
            Log.i("MyLog", "title: ${item.title}")
            Log.i("MyLog", "description: ${item.description}")
            Log.i("MyLog", "imageUrl: ${item.imageUrl}")
        }
    }
}
