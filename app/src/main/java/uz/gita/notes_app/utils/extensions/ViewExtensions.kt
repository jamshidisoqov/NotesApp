package uz.gita.notes_app.utils.extensions

import android.view.View

// Created by Jamshid Isoqov an 9/7/2022

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}