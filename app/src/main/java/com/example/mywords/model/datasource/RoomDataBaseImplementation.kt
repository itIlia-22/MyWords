package com.example.mywords.model.datasource

import com.example.mywords.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation:DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}