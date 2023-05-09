package com.example.mywords

import com.example.mywords.id.NAME_LOCAL
import com.example.mywords.id.NAME_REMOTE
import com.example.mywords.model.data.DataModel
import com.example.mywords.model.datasource.RetrofitImplementation
import com.example.mywords.model.datasource.RoomDataBaseImplementation
import com.example.mywords.model.repository.Repository
import com.example.mywords.model.repository.RepositoryImpl
import com.example.mywords.view.main.MainInteractor
import com.example.mywords.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImpl(RetrofitImplementation())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImpl(RoomDataBaseImplementation())
    }
}
    val mainScreen = module {
        factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
        factory { MainViewModel(get()) }
    }
