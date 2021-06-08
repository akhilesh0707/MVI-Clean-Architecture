package com.aqube.mvi.common.mvi

interface IReducer<STATE, T : Any> {
    fun reduce(result: Result<T>, state: STATE): STATE
}