package com.aqube.mvi.presentation.features.category

import com.aqube.mvi.domain.interactor.GetCategoriesUseCase
import com.aqube.mvi.presentation.common.BaseViewModel
import com.aqube.mvi.presentation.common.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
@InternalCoroutinesApi
class CategoriesViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val categoriesUseCase: GetCategoriesUseCase
) : BaseViewModel<CategoryIntent, CategoryAction, CategoryState>(contextProvider) {

    override fun intentToAction(intent: CategoryIntent): CategoryAction {
        return when (intent) {
            is CategoryIntent.LoadAllCategories -> CategoryAction.AllCategories
        }
    }

    override fun handleAction(action: CategoryAction) {
        launchCoroutineIO {
            when (action) {
                is CategoryAction.AllCategories -> {
                    categoriesUseCase(Unit).collect {
                        viewState.postValue(it.reduce())
                    }
                }
            }
        }
    }
}

