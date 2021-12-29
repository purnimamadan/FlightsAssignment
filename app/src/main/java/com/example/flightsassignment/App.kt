package com.example.flightsassignment

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }

    companion object {
        private var sInstance: App? = null
        val instance: App
            get() = sInstance!!
    }
}