package com.aqube.mvi.domain.interactor

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.domain.model.NewsResponse
import com.aqube.mvi.domain.repository.NewsRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetTopHeadingListBaseUseCase = BaseUseCase<Int, NewsResponse>

class GetTopHeadingListUseCase @Inject constructor(
    private val newsRemoteRepository: NewsRemoteRepository
) : GetTopHeadingListBaseUseCase {
    override suspend operator fun invoke(params: Int) = newsRemoteRepository.getTopHeadings(10,params,"IN")
}