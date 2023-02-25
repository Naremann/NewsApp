package com.example.newsapp.repos.sources

import com.example.newsapp.ConstanttVariables
import com.example.newsapp.api.WebServices
import com.example.newsapp.model.SourcesItem

class OnlineSourcesDataSourceImp(val webServices: WebServices):OnlineSourcesDataSource {
    override suspend fun getOnlineSources(category: String,language:String): List<SourcesItem?>? {
        try {
            val result = webServices.getSourcesWithLang(ConstanttVariables.API_KEY,category,language)
            return result.sources
        }
        catch (ex:Exception){
            throw ex
        }
    }
}