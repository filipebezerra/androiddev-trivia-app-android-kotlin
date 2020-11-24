package dev.filipebezerra.android.androiddevtrivia

import android.app.Application
import timber.log.Timber

class TriviaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}