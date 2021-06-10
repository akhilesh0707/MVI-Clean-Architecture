package com.aqube.mvi.presentation.features.articlelist.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aqube.mvi.domain.interactor.GetTopHeadingListUseCase
import com.aqube.mvi.domain.model.Article
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(
    private val topHeadingListUseCase: GetTopHeadingListUseCase
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        return try {
            val response = topHeadingListUseCase(position)
            val nextKey = if (loadMore(response.totalResults, position, params.loadSize)) {
                position + 1
            } else {
                null
            }

            LoadResult.Page(
                data = response.articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    /**
     * Check is last page is loaded or not
     * @param totalRecord : Total number of news
     * @param currentPage : Current page load
     * @return is loading required or not
     */
    private fun loadMore(totalRecord: Int, currentPage: Int, pageSize: Int): Boolean {
        val totalPage = (totalRecord / pageSize)
        return totalPage >= currentPage
    }
}
