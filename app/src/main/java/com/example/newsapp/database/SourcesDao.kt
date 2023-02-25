package com.example.newsapp.database

import androidx.room.*
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.Source
import com.example.newsapp.model.SourcesItem


@Dao
interface SourcesDao {

    @Query("select * from SourcesItem where category =:category ")
     suspend fun getSourcesByCategory(category: String):List<SourcesItem?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources : List<SourcesItem?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNews(news:List<ArticlesItem?>?)

    @Query("select * from ArticlesItem where source =:source")
    suspend fun getNews(source:Source):List<ArticlesItem?>?

}