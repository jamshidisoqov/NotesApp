package uz.gita.notes_app

import android.app.Application
import com.yalantis.ucrop.BuildConfig
import timber.log.Timber
import uz.gita.notes_app.data.source.local.AppDatabase

// Created by Jamshid Isoqov an 9/6/2022
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppDatabase.init(this)
    }
}