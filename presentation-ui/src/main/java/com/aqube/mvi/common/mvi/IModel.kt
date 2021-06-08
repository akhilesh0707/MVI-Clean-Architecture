package com.aqube.mvi.common.mvi

import androidx.lifecycle.LiveData

interface IModel<STATE, INTENT> {
    val state: LiveData<STATE>
    fun dispatchIntent(intent: INTENT)
}