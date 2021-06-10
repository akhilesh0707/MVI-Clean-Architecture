package com.aqube.mvi.presentation.features.articlelist.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.presentation.common.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleDataSourceFactory @Inject constructor(private val dataSource: ArticlePagingSource) {
    fun getTopArticleList(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { dataSource }
        ).flow
    }
}
