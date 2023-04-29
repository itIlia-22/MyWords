package com.example.mywords.view.main

import com.example.mywords.model.repository.Repository
import com.example.mywords.model.data.AppState
import com.example.mywords.model.data.DataModel
import com.example.mywords.viewmodel.Interactor
import io.reactivex.Observable

class MainInteractor(
// Снабжаем интерактор репозиторием для получения локальных или внешних
// данных
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
): Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
    return  if(fromRemoteSource){
          remoteRepository.getData(word).map { AppState.Success(it) }
      }else{
        localRepository.getData(word).map { AppState.Success(it) }
      }
    }
}