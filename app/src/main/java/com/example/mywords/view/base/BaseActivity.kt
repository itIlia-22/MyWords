package com.example.mywords.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mywords.model.data.AppState
import com.example.mywords.viewmodel.BaseViewModel
import com.example.mywords.viewmodel.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)
}