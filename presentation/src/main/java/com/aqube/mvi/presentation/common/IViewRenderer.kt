package com.aqube.mvi.presentation.common

interface IViewRenderer<STATE> {
    fun render(state: STATE)
}