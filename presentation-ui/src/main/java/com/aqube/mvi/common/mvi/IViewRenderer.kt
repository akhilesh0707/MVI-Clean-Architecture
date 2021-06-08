package com.aqube.mvi.common.mvi

interface IViewRenderer<STATE> {
    fun render(state: STATE)
}