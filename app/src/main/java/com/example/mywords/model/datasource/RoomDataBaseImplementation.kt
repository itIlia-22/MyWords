package com.example.mywords.model.datasource

import com.example.mywords.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented")
    }
}