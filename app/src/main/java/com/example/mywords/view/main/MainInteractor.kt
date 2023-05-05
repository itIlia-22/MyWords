package com.example.mywords.view.main

import com.example.mywords.id.NAME_LOCAL
import com.example.mywords.id.NAME_REMOTE
import com.example.mywords.model.repository.Repository
import com.example.mywords.model.data.AppState
import com.example.mywords.model.data.DataModel
import com.example.mywords.viewmodel.Interactor
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
    return  if(fromRemoteSource){
        repositoryRemote.getData(word).map { AppState.Success(it) }
      }else{
        repositoryLocal.getData(word).map { AppState.Success(it) }
      }
    }
}