package com.example.androidstudyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.Ingredient
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController by lazy { findNavController(R.id.mainContainer) }

    private val threadPool: ExecutorService = Executors.newFixedThreadPool(10)

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

        Log.i("MyLog", "   ")
        Log.i("MyLog", "Метод onCreate() выполняется на потоке: Main")

        thread {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .build()

            val request: Request = Request.Builder()
                .url("https://recipes.androidsprint.ru/api/category")
                .build()

            okHttpClient.newCall(request).execute().use { it ->
                Log.i("MyLog", "Выполняю запрос в отдельном, НЕ UI, потоке")
                Log.i("MyLog", "responseBody: ${it.code}")
                Log.i("MyLog", "responseBody: ${it.body?.string()}")
                Log.i("MyLog", "_________________________________")
            }

            val response = okHttpClient.newCall(request).execute().body?.string()

            val categories = response?.let { parseResponse(it) }

            categories?.forEach {
                threadPool.execute {

                    val recipesUrl: Request = Request.Builder()
                        .url("https://recipes.androidsprint.ru/api/category")
                        .build()

                    val responseRecipesUrl = okHttpClient.newCall(request).execute().body?.string()

                    val recipes = responseRecipesUrl?.let { it1 -> parseRecipesListResponse(it1) }
                    Log.i("MyLog", recipes.toString())
                }
            }
        }
    }

    private fun parseResponse(response: String): List<Category> {

        val listCategory = mutableListOf<Category>()
        val responseObject = JSONArray(response)
        for (el in 0 until responseObject.length()) {
            val item = Category(
                responseObject.getJSONObject(el).getInt("id"),
                responseObject.getJSONObject(el).getString("title"),
                responseObject.getJSONObject(el).getString("description"),
                responseObject.getJSONObject(el).getString("imageUrl"),
            )
            listCategory.add(item)

            Log.i("MyLog", item.toString())
        }
        return listCategory
    }

    private fun parseRecipesListResponse(response: String): List<Recipe> {

        val listRecipe = mutableListOf<Recipe>()
        val responseRecipesObject = JSONArray(response)
        for (el in 0 until responseRecipesObject.length()) {
            val item = Recipe(
                id = responseRecipesObject.getJSONObject(el).getInt("id"),
                title = responseRecipesObject.getJSONObject(el).getString("title"),
                imageUrl = responseRecipesObject.getJSONObject(el).getString("imageUrl"),
                ingredients = parsingIngredientsList(
                    responseRecipesObject.getJSONObject(el).getJSONArray("ingredients")
                ),
                method = parsingMethodList(
                    responseRecipesObject.getJSONObject(el).getJSONArray("method")
                )
            )
            listRecipe.add(item)
        }
        return listRecipe
    }

    private fun parsingIngredientsList(ingredients: JSONArray): List<Ingredient> {
        val list = mutableListOf<Ingredient>()
        for (el in 0 until ingredients.length()) {
            val itemIngredient = Ingredient(
                quantity = ingredients.getJSONObject(el).getString("quantity"),
                unitOfMeasure = ingredients.getJSONObject(el).getString("unitOfMeasure"),
                description = ingredients.getJSONObject(el).getString("description")
            )
            list.add(itemIngredient)
        }
        return list
    }

    private fun parsingMethodList(method: JSONArray): List<String> {
        val list = mutableListOf<String>()
        for (el in 0 until method.length()) {
            val itemMethod = method.getString(el)
            list.add(itemMethod)
        }
        return list
    }
}