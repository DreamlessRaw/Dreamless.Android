package com.example.dreamless.tools

import android.app.Application
import android.content.Context

class AndroidApplication : Application() {

    companion object {
        private lateinit var context: Context
        private lateinit var instance: Application
        fun getContext(): Context = this.context
        fun getInstance(): Application = this.instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this.applicationContext
    }

}