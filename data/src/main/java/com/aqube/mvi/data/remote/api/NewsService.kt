package com.aqube.mvi.data.remote.api

import com.aqube.mvi.data.remote.api.Constants.GET_TOP_HEADINGS
import com.aqube.mvi.data.remote.api.Constants.KEY_QUERY_API_KEY
import com.aqube.mvi.data.remote.api.Constants.KEY_QUERY_COUNTRY
import com.aqube.mvi.data.remote.api.Constants.KEY_QUERY_PAGE
import com.aqube.mvi.data.remote.api.Constants.KEY_QUERY_PAGE_SIZE
import com.aqube.mvi.domain.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(GET_TOP_HEADINGS)
    suspend fun getTopHeadings(
        @Query(KEY_QUERY_PAGE_SIZE) pageSize: Int,
        @Query(KEY_QUERY_PAGE) page: Int,
        @Query(KEY_QUERY_COUNTRY) country: String,
    ): NewsResponse

}
