package com.example.mywords.app

import android.app.Application
import com.example.mywords.id.application
import com.example.mywords.id.historyScreen
import com.example.mywords.id.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}