package com.example.mywords.model.datasource

import com.example.mywords.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}