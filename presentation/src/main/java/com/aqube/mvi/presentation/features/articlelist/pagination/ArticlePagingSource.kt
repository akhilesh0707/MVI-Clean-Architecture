package com.aqube.mvi.presentation.features.articlelist.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aqube.mvi.domain.common.CallErrors
import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.domain.model.NewsResponse
import com.aqube.mvi.presentation.common.Constants.FIRST_PAGE
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(
    private val func: suspend (pageNumber: Int, pageSize: Int) -> Result<NewsResponse>
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: FIRST_PAGE
        return when (val apiEvent = func.invoke(position, params.loadSize)) {
            is Result.Error -> LoadResult.Error(
                when (apiEvent.exception) {
                    CallErrors.ErrorEmptyData -> Throwable("Empty data")
                    is CallErrors.ErrorException -> (apiEvent.exception as CallErrors.ErrorException).throwable
                    CallErrors.ErrorServer -> Throwable("Server error")
                }
            )
            is Result.Success -> LoadResult.Page(
                data = apiEvent.data.articles,
                prevKey = if (position == FIRST_PAGE) null else position - 1,
                nextKey = if (apiEvent.data.articles.isEmpty()) null else position + 1
            )
            else -> LoadResult.Error(Throwable("No Data Found."))
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
}
