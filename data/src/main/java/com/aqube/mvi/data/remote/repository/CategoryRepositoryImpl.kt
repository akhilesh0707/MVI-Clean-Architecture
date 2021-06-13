package com.aqube.mvi.data.remote.repository

import com.aqube.mvi.domain.common.CallErrors
import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.categories.Category
import com.aqube.mvi.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {
    override suspend fun getCategories(): Flow<Result<List<Category>>> = flow {
        getData().run {
            if (this.isNotEmpty()) {
                emit(Result.Success(this))
            } else {
                emit(Result.Error(CallErrors.ErrorEmptyData))
            }
        }
    }

    //This should be came from api but we don't have api so we are crating locally
    private fun getData(): List<Category> {
        val categories = mutableListOf<Category>()
        categories.add(Category(1, "Health", "ic_health"))
        categories.add(Category(2, "Politics", "ic_politics"))
        categories.add(Category(3, "Business", "ic_business"))
        categories.add(Category(4, "Sports", "ic_sports"))
        categories.add(Category(5, "Music", "ic_music"))
        categories.add(Category(6, "General", "ic_newspaper"))
        categories.add(Category(7, "Technology", "ic_technology"))
        categories.add(Category(8, "Science", "ic_science"))
        return categories
    }

}