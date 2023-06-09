package com.example.mywords.view.main

import com.example.mywords.model.data.AppState
import com.example.mywords.model.data.DataModel
import com.example.mywords.model.repository.Repository
import com.example.mywords.model.repository.RepositoryLocal
import com.example.mywords.viewmodel.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)

        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState

    }
}
