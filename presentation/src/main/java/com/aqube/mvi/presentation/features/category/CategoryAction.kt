package com.aqube.mvi.presentation.features.category

import com.aqube.mvi.presentation.common.ViewAction

sealed class CategoryAction : ViewAction {
    object AllCategories : CategoryAction()
}