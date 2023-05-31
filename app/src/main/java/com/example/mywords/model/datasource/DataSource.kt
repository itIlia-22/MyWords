package com.example.mywords.model.datasource

import io.reactivex.Observable

// Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSource<T> {

    suspend fun getData(word: String): T
}
