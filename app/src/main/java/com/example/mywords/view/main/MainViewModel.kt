package com.example.mywords.view.main

import androidx.lifecycle.LiveData
import com.example.mywords.model.data.AppState
import com.example.mywords.model.datasource.DataSourceLocal
import com.example.mywords.model.datasource.DataSourceRemote
import com.example.mywords.model.repository.RepositoryImpl
import com.example.mywords.view.base.View
import com.example.mywords.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableObserver

class MainViewModel<T : AppState, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImpl(DataSourceRemote()),
        RepositoryImpl(DataSourceLocal())
    )

) : BaseViewModel<AppState>() {
    // В этой переменной хранится последнее состояние Activity
    private var appState: AppState? = null


    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
// Как только начинается загрузка, передаём во View модель данных для
// отображения экрана загрузки
                .doOnSubscribe { liveDataFotViewToObserve.value = (AppState.Loading(null)) }
                .subscribeWith(getObserver())

        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
// Данные успешно загружены; сохраняем их и передаем во View (через
// LiveData). View сама разберётся, как их отображать
                appState = state
                liveDataFotViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
// В случае ошибки передаём её в Activity таким же образом через LiveData
                liveDataFotViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }


}

