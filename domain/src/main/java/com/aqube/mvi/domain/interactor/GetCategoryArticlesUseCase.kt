package com.aqube.mvi.domain.interactor

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.articles.NewsResponse
import com.aqube.mvi.domain.repository.ArticleRemoteRepository
import javax.inject.Inject

typealias GetCategoryArticlesBaseUseCase = BaseUseCase<GetCategoryArticlesUseCase.Params, Result<NewsResponse>>

class GetCategoryArticlesUseCase @Inject constructor(
    private val articleRemoteRepository: ArticleRemoteRepository
) : GetCategoryArticlesBaseUseCase {
    override suspend operator fun invoke(params: Params) =
        articleRemoteRepository.categoryArticle(
            pageSize = params.pageSize,
            pageNumber = params.pageNumber,
            country = params.country,
            category = params.category
        )

    data class Params(
        val pageSize: Int,
        val pageNumber: Int,
        val country: String,
        val category: String
    )
}
