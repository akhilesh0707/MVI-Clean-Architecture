package com.aqube.mvi.domain.interactor

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.articles.NewsResponse
import com.aqube.mvi.domain.model.categories.Category
import com.aqube.mvi.domain.repository.ArticleRemoteRepository
import com.aqube.mvi.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetCategoriesBaseUseCase = BaseUseCase<Unit, Flow<Result<List<Category>>>>

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoriesBaseUseCase {
    override suspend operator fun invoke(params: Unit) = categoryRepository.getCategories()

}