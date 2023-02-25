package com.example.newsapp.api

import com.example.newsapp.model.ArticleResponses
import com.example.newsapp.model.SourcesRespones
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//interface to return data of tabs(sources) by using anotations
interface WebServices {

    @GET("/v2/top-headlines/sources")
    //get responses of sources(sources->texts of tabs)
    suspend fun getSourcesWithLang(
        //send your my api key
        @Query("apiKey") apiKey: String,
        @Query("category") category: String,
        @Query("language") language: String
    ): SourcesRespones //to call data of sources


    @GET("/v2/everything")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourceId: String
    ): ArticleResponses


    @GET("/v2/everything")
    suspend fun getArticlesWithPages(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourceId: String,
        @Query("page") page: Int
    ): ArticleResponses

    @GET("/v2/everything")
    fun searchArticles(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourceId: String,
        @Query("q")querySearch:String,
    ): Call<ArticleResponses>
}

