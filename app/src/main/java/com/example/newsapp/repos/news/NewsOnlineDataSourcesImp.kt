package com.example.newsapp.repos.news

import com.example.newsapp.ConstanttVariables
import com.example.newsapp.api.WebServices
import com.example.newsapp.model.ArticlesItem
import retrofit2.Callback

class NewsOnlineDataSourcesImp(val webServices:WebServices) : NewsOnlineDataSource {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = webServices.getArticles(ConstanttVariables.API_KEY,sourceId)
            return result.articles

        }catch (exception : Exception){
            throw exception
        }
    }
}