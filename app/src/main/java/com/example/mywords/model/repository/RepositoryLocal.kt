package com.example.mywords.model.repository

import com.example.mywords.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}