package com.example.mywords.view.main

import com.example.mywords.model.data.AppState
import com.example.mywords.model.datasource.DataSourceLocal
import com.example.mywords.model.datasource.DataSourceRemote
import com.example.mywords.model.repository.RepositoryImpl
import com.example.mywords.presenter.Presenter
import com.example.mywords.view.base.View
import com.example.mywords.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MainPresenterImp<T : AppState, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImpl(DataSourceRemote()),
        RepositoryImpl(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()

) : Presenter<T, V> {
    //ссылка на view без еонтекста
    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
// Как только начинается загрузка, передаём во View модель данных для
// отображения экрана загрузки
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())

        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
// Если загрузка окончилась успешно, передаем модель с данными
// для отображения
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
// Если произошла ошибка, передаем модель с ошибкой
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }


}

