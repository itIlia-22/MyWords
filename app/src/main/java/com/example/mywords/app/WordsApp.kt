package com.example.mywords.app

import android.app.Application
import com.example.mywords.id.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WordsApp  : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
    override fun onCreate() {
        super.onCreate()
        AppComponent.Builder()
            .application(this)
            .build()
            .inject(this)
    }
}