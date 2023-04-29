package com.example.mywords.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mywords.model.data.AppState
import com.example.mywords.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataFotViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable =
        CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()

) : ViewModel() {
    // Метод, благодаря которому Activity подписывается на изменение данных,
// возвращает LiveData, через которую и передаются данные
    open fun getData(word:String,isOnline:Boolean): LiveData<T>{
        return liveDataFotViewToObserve
    }

    // Единственный метод класса ViewModel, который вызывается перед
// уничтожением Activity
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}