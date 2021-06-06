package com.aqube.mvi.domain.interactor

import com.aqube.mvi.domain.model.NewsResponse
import com.aqube.mvi.domain.repository.NewsRemoteRepository
import javax.inject.Inject

typealias GetTopHeadingListBaseUseCase = BaseUseCase<Unit, NewsResponse>

class GetTopHeadingListUseCase @Inject constructor(
    private val newsRemoteRepository: NewsRemoteRepository
) : GetTopHeadingListBaseUseCase {
    override suspend operator fun invoke(params: Unit) = newsRemoteRepository.getTopHeadings()
}