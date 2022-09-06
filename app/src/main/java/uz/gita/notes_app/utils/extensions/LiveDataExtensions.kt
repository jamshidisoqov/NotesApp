package uz.gita.notes_app.utils.extensions

import androidx.lifecycle.MutableLiveData

// Created by Jamshid Isoqov an 9/6/2022


fun emptyLiveData() = MutableLiveData<Unit>()

fun <T> eventLiveData() = MutableLiveData<T>()

