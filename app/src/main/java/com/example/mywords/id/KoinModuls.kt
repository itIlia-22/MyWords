package com.example.mywords.id

import androidx.room.Room
import com.example.mywords.model.data.DataModel
import com.example.mywords.model.datasource.RetrofitImplementation
import com.example.mywords.model.datasource.RoomDataBaseImplementation
import com.example.mywords.model.repository.Repository
import com.example.mywords.model.repository.RepositoryImplementation
import com.example.mywords.model.repository.RepositoryImplementationLocal
import com.example.mywords.model.repository.RepositoryLocal
import com.example.mywords.room.HistoryDataBase
import com.example.mywords.view.history.HistoryInteractor
import com.example.mywords.view.history.HistoryViewModel
import com.example.mywords.view.main.MainInteractor
import com.example.mywords.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}