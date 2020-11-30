package com.ashu.bsctask

import android.app.Application

class Application : Application() {
    companion object {
        lateinit var instance: com.ashu.bsctask.Application private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}