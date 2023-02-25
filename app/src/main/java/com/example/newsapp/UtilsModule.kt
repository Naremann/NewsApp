package com.example.newsapp

import android.content.Context
import com.example.newsapp.ConstanttVariables
import com.example.newsapp.NetworkStatusHandler
import com.example.newsapp.NetworkStatusHandlerImp
import com.example.newsapp.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {
    @Provides
    fun ProvideNetworkStatusHandler(@ApplicationContext context: Context): NetworkStatusHandler {
        return NetworkStatusHandlerImp(context)

    }
    @Singleton
    @Provides
    fun provideDatabase(): MyDatabase {
        return MyDatabase.getInstance()
    }
}