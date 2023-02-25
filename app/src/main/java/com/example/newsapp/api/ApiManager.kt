package com.example.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// to get all apis
class ApiManager {
    companion object{
        //get url of sources
        private const val BASE_URL = "https://newsapi.org"
        //retrofit is a library
        var retrofit : Retrofit? = null
        private fun getInstance() : Retrofit{
            if(retrofit == null){
                // if retrofit is equal to null,build object of it(retrofit)
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.create to create object of retrofit
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!

        }
        fun getApis() : WebServices{
            // getInstance to build retrofit object
            return getInstance().create(WebServices :: class.java)
        }
    }
}