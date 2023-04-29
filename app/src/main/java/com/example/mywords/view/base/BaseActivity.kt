package com.example.mywords.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mywords.model.data.AppState
import com.example.mywords.presenter.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>
    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }
//Если вию готова отображать,передаем ссылку на вию призентер
    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }
//удаляем если приложение пересоздается
    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

}