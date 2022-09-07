package uz.gita.notes_app.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

// Created by Jamshid Isoqov an 9/6/2022

fun getCurrentDate(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
}