package com.example.newsapp.repos.sources

import com.example.newsapp.database.MyDatabase
import com.example.newsapp.model.SourcesItem

class OfflineSourcesDataSourceImp(val myDatabase: MyDatabase):OfflineSourcesDataSource {
    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        myDatabase.sourceDao().updateSources(sources = sources)
    }

    override suspend fun getOfflineSources(
        category: String,
        language: String
    ): List<SourcesItem?>? {
        val result = myDatabase.sourceDao().getSourcesByCategory(category)
        return result
    }
}