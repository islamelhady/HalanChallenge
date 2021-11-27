package com.example.halanchallenge.util

import android.view.View

fun View.makeItVisible() {
    this.visibility = View.VISIBLE
    this.alpha = 1f
}
fun View.makeItInVisible() {
    this.visibility = View.INVISIBLE
    this.alpha = 0f
}