package com.example.newsapp.repos.news

import android.net.Network
import com.example.newsapp.NetworkStatusHandler
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.Source

class NewsRepositoryImp(val onlineSource: NewsOnlineDataSource,
    val newsOfflineDataSource: NewsOfflineDataSource
,val networkStatusHandler: NetworkStatusHandler) : NewsRepository {
    override suspend fun getNews(source: Source): List<ArticlesItem?>? {
        try {
            if (networkStatusHandler.isOnline()) {
                val result = onlineSource.getNews(source.id as String)
                newsOfflineDataSource.updateNews(result)
                return result
            }
            return newsOfflineDataSource.getNews(source)
        } catch (ex: Exception) {
            return newsOfflineDataSource.getNews(source)

        }
    }}