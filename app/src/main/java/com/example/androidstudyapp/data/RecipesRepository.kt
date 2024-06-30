package com.example.androidstudyapp.data

import com.example.androidstudyapp.data.db.DataBase
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

    private val recipeApiService: RecipeApiService = retrofit.create(RecipeApiService::class.java)

    private val dataBase = DataBase.getDataBase(RecipesApplication.instance)

    private val categoriesDao = dataBase.getCategoryDao()

    private val recipesDao = dataBase.getRecipesDao()

    suspend fun getCategories(): List<Category>? = withContext(Dispatchers.IO) {
        return@withContext try {
            val newDataFromNetwork = recipeApiService.getListCategory().execute().body()
            if (newDataFromNetwork != null) {
                categoriesDao.addCategoryToList(newDataFromNetwork)
            }
            newDataFromNetwork
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

    suspend fun getCategoriesFromCache(): List<Category> = withContext(Dispatchers.IO){
        return@withContext categoriesDao.getListAllCategory()
    }

    suspend fun getRecipesFromCache(): List<Recipe> = withContext(Dispatchers.IO){
        return@withContext recipesDao.getListAllRecipes()
    }
}
