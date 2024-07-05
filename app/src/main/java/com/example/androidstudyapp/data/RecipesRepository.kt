package com.example.androidstudyapp.data

import com.example.androidstudyapp.data.db.CategoriesDao
import com.example.androidstudyapp.data.db.RecipesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    val categoriesDao: CategoriesDao,
    val recipesDao: RecipesDao,
    val recipeApiService: RecipeApiService,
) {

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

    suspend fun getCategoriesFromCache(): List<Category> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.getListAllCategory()
    }

    suspend fun getRecipesFromCache(categoryId: Int): List<Recipe> = withContext(Dispatchers.IO) {
        return@withContext recipesDao.getListAllRecipes()
    }

    suspend fun getListFavouriteRecipes(): List<Recipe> = withContext(Dispatchers.IO) {
        return@withContext recipesDao.getListFavouriteRecipes()
    }

    suspend fun updateRecipe(recipe: Recipe) = withContext(Dispatchers.IO) {
        recipesDao.updateRecipe(recipe)
    }
}