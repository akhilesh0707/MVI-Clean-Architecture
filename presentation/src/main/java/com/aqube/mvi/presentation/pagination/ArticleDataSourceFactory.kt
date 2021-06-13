package com.aqube.mvi.presentation.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aqube.mvi.domain.interactor.GetCategoryArticlesUseCase
import com.aqube.mvi.domain.interactor.GetSearchArticleUseCase
import com.aqube.mvi.domain.interactor.GetTopArticleListUseCase
import com.aqube.mvi.domain.model.articles.Article
import com.aqube.mvi.presentation.common.Constants.COUNTRY
import com.aqube.mvi.presentation.common.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleDataSourceFactory @Inject constructor(
    private val topArticleListUseCase: GetTopArticleListUseCase,
    private val searchArticleUseCase: GetSearchArticleUseCase,
    private val categoryArticleUseCase: GetCategoryArticlesUseCase
) {
    fun getTopArticleList(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                ArticlePagingSource { pageNumber, pageSize ->
                    topArticleListUseCase(
                        GetTopArticleListUseCase.Params(pageSize, pageNumber, COUNTRY)
                    )
                }
            }
        ).flow
    }

    fun searchArticles(searchQuery: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                ArticlePagingSource { pageNumber, pageSize ->
                    searchArticleUseCase(
                        GetSearchArticleUseCase.Params(pageSize, pageNumber, searchQuery)
                    )
                }
            },
        ).flow
    }

    fun getCategoryArticles(category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                ArticlePagingSource { pageNumber, pageSize ->
                    categoryArticleUseCase(
                        GetCategoryArticlesUseCase.Params(pageSize, pageNumber, COUNTRY, category)
                    )
                }
            },
        ).flow
    }

}
