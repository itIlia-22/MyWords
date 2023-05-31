package com.example.mywords.view.history

import com.example.mywords.model.data.AppState
import com.example.mywords.model.data.DataModel
import com.example.mywords.model.repository.Repository
import com.example.mywords.model.repository.RepositoryLocal
import com.example.mywords.viewmodel.Interactor
import io.reactivex.Observable

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
