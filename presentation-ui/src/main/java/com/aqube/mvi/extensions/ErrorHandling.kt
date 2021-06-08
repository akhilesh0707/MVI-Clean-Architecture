package com.aqube.mvi.extensions

import android.content.Context
import com.aqube.mvi.R
import com.aqube.mvi.domain.common.CallErrors

fun CallErrors.getMessage(context: Context): String {
    return when (this) {
        is CallErrors.ErrorEmptyData -> context.getString(R.string.error_empty_data)
        is CallErrors.ErrorServer -> context.getString(R.string.error_server_error)
        is CallErrors.ErrorException -> context.getString(R.string.error_exception)
    }
}