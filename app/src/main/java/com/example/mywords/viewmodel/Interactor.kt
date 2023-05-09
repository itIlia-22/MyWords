package com.example.mywords.viewmodel

import io.reactivex.Observable

//level 2 бизнес-логика
interface Interactor<T>{
    // Use Сase: получение данных для вывода на экран

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}