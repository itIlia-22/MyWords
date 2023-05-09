package com.example.mywords.app

import android.app.Application
import com.example.mywords.application
import com.example.mywords.mainScreen
import org.koin.core.context.startKoin

class WordsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}