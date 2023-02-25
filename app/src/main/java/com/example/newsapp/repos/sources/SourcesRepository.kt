package com.example.newsapp.repos.sources

import com.example.newsapp.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources(category: String,language:String):List<SourcesItem?>?

}
interface OnlineSourcesDataSource{
    suspend fun getOnlineSources(category:String,language:String):List<SourcesItem?>?
}
interface OfflineSourcesDataSource{

    suspend fun updateSources(sources:List<SourcesItem?>?)
    suspend fun getOfflineSources(category:String,language: String):List<SourcesItem?>?
}

