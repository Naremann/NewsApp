package com.example.newsapp.repos.news

import com.example.newsapp.database.MyDatabase
import com.example.newsapp.database.SourcesDao
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.Source

class NewsOfflineDataSourceImp(val database: MyDatabase) : NewsOfflineDataSource {
    override suspend fun updateNews(news: List<ArticlesItem?>?) {
        database.sourceDao().updateNews(news)
    }

    override suspend fun getNews(source:Source): List<ArticlesItem?>? {
        return database.sourceDao().getNews(source)
    }
}