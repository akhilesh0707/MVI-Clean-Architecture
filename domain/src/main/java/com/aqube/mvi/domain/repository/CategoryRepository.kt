package com.aqube.mvi.domain.repository

import com.aqube.mvi.domain.model.categories.Category
import kotlinx.coroutines.flow.Flow
import com.aqube.mvi.domain.common.Result

interface CategoryRepository {
    suspend fun getCategories(): Flow<Result<List<Category>>>
}