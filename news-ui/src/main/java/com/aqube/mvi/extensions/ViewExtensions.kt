package com.aqube.mvi.extensions

import android.view.View

/**
 * Shorthand extension function to make view gone
 */
fun View.makeGone() {
    this.visibility = View.GONE
}

/**
 * Shorthand extension function to make view visible
 */
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }