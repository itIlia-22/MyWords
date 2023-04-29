package com.example.mywords.presenter

import io.reactivex.Observable

//level 2 бизнес-логика
interface Interactor<T>{
    // Use Сase: получение данных для вывода на экран

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}