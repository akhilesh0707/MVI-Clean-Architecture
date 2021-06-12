package com.aqube.mvi.domain.interactor

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.articles.NewsResponse
import com.aqube.mvi.domain.repository.ArticleRemoteRepository
import javax.inject.Inject

typealias GetSearchArticleBaseUseCase = BaseUseCase<GetSearchArticleUseCase.Params, Result<NewsResponse>>

class GetSearchArticleUseCase @Inject constructor(
    private val articleRemoteRepository: ArticleRemoteRepository
) : GetSearchArticleBaseUseCase {
    override suspend operator fun invoke(params: Params) =
        articleRemoteRepository.searchArticle(
            pageSize = params.pageSize,
            pageNumber = params.pageNumber,
            searchQuery = params.searchQuery
        )

    data class Params(var pageSize: Int, var pageNumber: Int, var searchQuery: String)
}