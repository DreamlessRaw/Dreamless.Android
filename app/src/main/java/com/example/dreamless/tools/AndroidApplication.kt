package com.example.dreamless.tools

import android.app.Application
import android.content.Context

class AndroidApplication : Application() {

    companion object {
        private lateinit var context: Context
        fun getContext(): Context {
            return this.context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

}