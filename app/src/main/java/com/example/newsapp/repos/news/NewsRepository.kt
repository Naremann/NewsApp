package com.example.newsapp.repos.news

import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.Source

interface NewsRepository {
    suspend fun getNews(source: Source):List<ArticlesItem?>?


}
interface NewsOnlineDataSource{
    suspend fun getNews(sourceId:String):List<ArticlesItem?>?
}
interface NewsOfflineDataSource{
    suspend fun updateNews(news:List<ArticlesItem?>?)
    suspend fun getNews(source:Source): List<ArticlesItem?>?
}