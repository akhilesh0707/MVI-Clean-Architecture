package com.aqube.mvi.presentation.features.category

import com.aqube.mvi.presentation.common.ViewIntent

sealed class CategoryIntent : ViewIntent {
    object LoadAllCategories : CategoryIntent()
}