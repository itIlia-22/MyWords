package com.example.mywords.presenter

import com.example.mywords.model.data.AppState
import com.example.mywords.view.base.View

//level 1 the presenter doesn't know about the context or the framework
interface Presenter<T: AppState, V: View>{
    fun attachView(view: V)
    fun detachView(view: V)
    // Получение данных с флагом isOnline(из Интернета или нет)
    fun getData(word: String, isOnline: Boolean)

}