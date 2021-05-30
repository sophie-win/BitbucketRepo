package com.sophie.bitbucketrepo

import android.app.Application
import timber.log.Timber

class MyCustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}