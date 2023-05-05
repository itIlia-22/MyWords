package com.example.mywords.id

import com.example.mywords.model.data.DataModel
import com.example.mywords.model.datasource.DataSource
import com.example.mywords.model.datasource.RetrofitImplementation
import com.example.mywords.model.datasource.RoomDataBaseImplementation
import com.example.mywords.model.repository.Repository
import com.example.mywords.model.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote:
                                         DataSource<List<DataModel>>
    ): Repository<List<DataModel>> =
        RepositoryImpl(dataSourceRemote)
    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal:
                                        DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImpl(dataSourceLocal)
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()
    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}
