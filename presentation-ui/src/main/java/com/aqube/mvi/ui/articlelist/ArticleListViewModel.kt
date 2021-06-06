package com.aqube.mvi.ui.articlelist

import androidx.lifecycle.LiveData
import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.base.livedata.UiAwareLiveData
import com.aqube.mvi.base.livedata.UiAwareModel
import com.aqube.mvi.domain.interactor.GetTopHeadingListUseCase
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import javax.inject.Inject


sealed class ArticleUIModel : UiAwareModel() {
    object Loading : ArticleUIModel()
    data class Error(var error: String = "") : ArticleUIModel()
    data class Success(val data: List<Article>) : ArticleUIModel()
}

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val topHeadingListUseCase: GetTopHeadingListUseCase
) : BaseViewModel(contextProvider) {

    private val _articleList = UiAwareLiveData<ArticleUIModel>()
    private var articleList: LiveData<ArticleUIModel> = _articleList

    fun getArticles(): LiveData<ArticleUIModel> {
        return articleList
    }

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
    }

    fun getTopHeadings() {
        launchCoroutineIO {
            loadTopHeadings()
        }
    }

    private suspend fun loadTopHeadings() {
        topHeadingListUseCase.invoke(Unit).let {
            _articleList.postValue(ArticleUIModel.Success(it.articles))
        }
    }
}

