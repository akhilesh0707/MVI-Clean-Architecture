package com.aqube.mvi.data.remote.api

import com.aqube.mvi.data.remote.api.Constants.GET_EVERYTHING
import com.aqube.mvi.data.remote.api.Constants.GET_TOP_HEADINGS
import com.aqube.mvi.data.remote.api.Constants.QUERY_PARAM_CATEGORY
import com.aqube.mvi.data.remote.api.Constants.QUERY_PARAM_COUNTRY
import com.aqube.mvi.data.remote.api.Constants.QUERY_PARAM_PAGE
import com.aqube.mvi.data.remote.api.Constants.QUERY_PARAM_PAGE_SIZE
import com.aqube.mvi.data.remote.api.Constants.QUERY_PARAM_SEARCH
import com.aqube.mvi.domain.model.articles.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(GET_TOP_HEADINGS)
    suspend fun getTopHeadings(
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_COUNTRY) country: String,
    ): Response<NewsResponse>

    @GET(GET_EVERYTHING)
    suspend fun searchArticles(
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_SEARCH) searchQuery: String,
    ): Response<NewsResponse>

    @GET(GET_TOP_HEADINGS)
    suspend fun getCategoryArticles(
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_COUNTRY) country: String,
        @Query(QUERY_PARAM_CATEGORY) category: String,
    ): Response<NewsResponse>


}
