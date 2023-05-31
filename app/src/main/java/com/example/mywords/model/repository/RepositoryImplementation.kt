package com.example.mywords.model.repository

import com.example.mywords.model.data.DataModel
import com.example.mywords.model.datasource.DataSource
import com.example.mywords.model.repository.Repository


class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
