package com.example.mywords.model.repository

import com.example.mywords.model.datasource.DataSource
import com.example.mywords.model.data.DataModel
import io.reactivex.Observable


class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или
// внешний)
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }

}
