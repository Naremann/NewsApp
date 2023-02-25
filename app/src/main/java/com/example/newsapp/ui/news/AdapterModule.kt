package com.example.newsapp.ui.news

import com.example.newsapp.adapter.ArticleItemAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {

    @Provides
    fun provideNewsAdapter():ArticleItemAdapter{
        return ArticleItemAdapter()
    }
}