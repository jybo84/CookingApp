package com.example.androidstudyapp.data

import androidx.room.Room
import com.example.androidstudyapp.data.db.AppDataBase
import com.example.androidstudyapp.ui.RecipesApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RecipesRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("$API_BASE_URL/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val dataBase =
        Room.databaseBuilder(RecipesApplication.instance, AppDataBase::class.java, "appDataBase")
            .build()

    val categoriesDao = dataBase.getCategoriesDao()

    private val recipeApiService: RecipeApiService = retrofit.create(RecipeApiService::class.java)

    suspend fun getCategories(): List<Category>? = withContext(Dispatchers.IO) {
        return@withContext try {
            val categories = recipeApiService.getListCategory().execute().body()
            if (categories != null) {
                categoriesDao.addCategories(categories)
            }
            categories
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getRecipesByCategoryId(categoryId: Int): List<Recipe>? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                recipeApiService.getListRecipesByIdCategory(categoryId).execute().body()
            } catch (e: IOException) {
                null
            }
        }

    suspend fun getRecipeById(id: Int): Recipe? = withContext(Dispatchers.IO) {
        return@withContext try {
            recipeApiService.getRecipeById(id).execute().body()
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getRecipesByIds(listIdFavourites: List<Int>): List<Recipe>? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                recipeApiService.getListRecipesById(listIdFavourites).execute().body()
            } catch (e: IOException) {
                null
            }
        }

    suspend fun getCategoryById(id: Int): Category? = withContext(Dispatchers.IO) {
        return@withContext try {
            recipeApiService.getCategoryById(id).execute().body()
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getCategoriesFromCache(): List<Category> {
        return categoriesDao.getAllCategories()
    }
}
