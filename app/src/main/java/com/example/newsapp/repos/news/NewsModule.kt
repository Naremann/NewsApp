package com.example.newsapp.repos.news

import com.example.newsapp.NetworkStatusHandler
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.WebServices
import com.example.newsapp.database.MyDatabase
import com.example.newsapp.repos.sources.OfflineSourcesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    fun provideNewsOnlineDataSource(webServices: WebServices):NewsOnlineDataSource{
        return NewsOnlineDataSourcesImp(webServices)
    }

    @Provides
    fun provideNewsOfflineDataSource(database: MyDatabase):NewsOfflineDataSource{
        return NewsOfflineDataSourceImp(database)
    }

    @Provides
    fun provideNewsRepository(newsOnlineDataSource: NewsOnlineDataSource,
                              newsOfflineSourcesDataSource:NewsOfflineDataSource,
                              networkStatusHandler: NetworkStatusHandler):NewsRepository{
        return NewsRepositoryImp(newsOnlineDataSource,newsOfflineSourcesDataSource,networkStatusHandler)
    }

}