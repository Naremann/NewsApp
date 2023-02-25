package com.example.newsapp.repos.sources

import com.example.newsapp.NetworkStatusHandler
import com.example.newsapp.model.SourcesItem

class SourcesRepositoryImp(
    val onlineSources: OnlineSourcesDataSource
    ,val offlineSources : OfflineSourcesDataSource,
    val isOnline:NetworkStatusHandler):SourcesRepository{
    override suspend fun getSources(category: String, language: String): List<SourcesItem?>? {
       try {
           if(isOnline.isOnline()) {
               val result = onlineSources.getOnlineSources(category,language)
               //cashing data
               offlineSources.updateSources(result)
               return result
           }
           return offlineSources.getOfflineSources(category,language)
       }
       catch (ex:Exception){
           //in case data is not come (online)
           return offlineSources.getOfflineSources(category,language)
       }
    }
}