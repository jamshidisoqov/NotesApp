package uz.gita.notes_app.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import uz.gita.notes_app.MainActivity

// Created by Jamshid Isoqov an 9/7/2022
object Constants {
    fun goToPlayMarket(activity: MainActivity){
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=uz.gita.task_app")
                )
            )
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=uz.gita.task_app")
                )
            )
        }
    }
}