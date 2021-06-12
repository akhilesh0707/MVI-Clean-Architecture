package com.aqube.mvi.domain.interactor

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.articles.NewsResponse
import com.aqube.mvi.domain.repository.ArticleRemoteRepository
import javax.inject.Inject

typealias GetTopArticleListBaseUseCase = BaseUseCase<GetTopArticleListUseCase.Params, Result<NewsResponse>>

class GetTopArticleListUseCase @Inject constructor(
    private val articleRemoteRepository: ArticleRemoteRepository
) : GetTopArticleListBaseUseCase {
    override suspend operator fun invoke(params: Params) =
        articleRemoteRepository.getTopArticles(
            pageSize = params.pageSize,
            pageNumber = params.pageNumber,
            country = params.country
        )

    data class Params(var pageSize: Int, var pageNumber: Int, var country: String)
}