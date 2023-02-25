package com.example.newsapp.repos.sources

import android.provider.SyncStateContract
import com.example.newsapp.ConstanttVariables
import com.example.newsapp.NetworkStatusHandler
import com.example.newsapp.api.ApiManager
import com.example.newsapp.database.MyDatabase
import com.example.newsapp.repos.sources.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//provider 'functions inner it will provide dependencies'
@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideOnlineDataSource() : OnlineSourcesDataSource{
        return OnlineSourcesDataSourceImp(ApiManager.getApis())
    }

    @Provides
    fun provideOfflineDataSource(database: MyDatabase):OfflineSourcesDataSource{
        return OfflineSourcesDataSourceImp(database)
    }


    @Provides
    fun provideSourceRepos(onlineDataSource:OnlineSourcesDataSource,
    offlineDataSource:OfflineSourcesDataSource,networkStatusHandler: NetworkStatusHandler):SourcesRepository{
        return SourcesRepositoryImp(onlineDataSource,offlineDataSource,networkStatusHandler)
    }

}