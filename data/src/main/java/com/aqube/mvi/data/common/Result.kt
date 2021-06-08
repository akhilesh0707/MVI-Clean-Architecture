package com.aqube.mvi.data.common

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: CallErrors) : Result<Nothing>()
    object Loading : Result<Nothing>()
}