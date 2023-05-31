package com.example.mywords.model.datasource

import com.example.mywords.model.data.AppState
import com.example.mywords.model.data.DataModel
import com.example.mywords.room.HistoryDao
import com.example.mywords.utils.convertDataModelSuccessToEntity
import com.example.mywords.utils.mapHistoryEntityToSearchResult
import io.reactivex.Observable

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}