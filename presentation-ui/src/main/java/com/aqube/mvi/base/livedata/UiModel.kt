package com.aqube.mvi.base.livedata

interface UiModel

open class UiAwareModel : UiModel {
    var isRedelivered: Boolean = false
}