package com.example.newsapp.ui.news

import android.content.Context
import android.provider.SyncStateContract
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.*
import com.example.newsapp.api.ApiManager
import com.example.newsapp.database.MyDatabase
import com.example.newsapp.model.*
import com.example.newsapp.repos.news.*
import com.example.newsapp.repos.sources.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class NewsViewModel @Inject constructor(private val sourcesRepository: SourcesRepository, private val newsRepository: NewsRepository) : ViewModel() {
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    var prgressBarVisible = MutableLiveData<Boolean>()
    var newsList = MutableLiveData<List<ArticlesItem?>?>()
    fun getSources(context: Context, categoryItem: CategoryItem) {
        var data = LanguagesSettingsHelper.retreiveDataFromSharedPreferences("lang", context)
        Log.e("NewsViewModelClass", "date is :" + data)

            if (data.equals("ar")) {
                Log.e("loadArticles", "language : $data")
                LocaleHelper.setLocale(context, "ar")
                getSourceWithLanguage(categoryItem, "ar")
        } else {
            LocaleHelper.setLocale(context, "en")
            getSourceWithLanguage(categoryItem, "en")
    }




    }

    fun loadTabArticles(context: Context, source:Source, page: Int) {
        viewModelScope.launch {
         try {
             //val article = ApiManager.getApis().getArticlesPages(ConstanttVariables.API_KEY, sourceId = sourceId, page)
             val articles = newsRepository.getNews(source)
             newsList.value = articles
             Log.e("articels", "Articles are : " + articles)
         }catch (ex : Exception){
             Toast.makeText(context, "Failed to load news", Toast.LENGTH_LONG).show()
             Log.e("failInLoadArticles", "fail : " + ex.localizedMessage)
         }
        }
    }

    private fun getSourceWithLanguage(categoryItem: CategoryItem, lang: String) {
        viewModelScope.launch {
            try {
                prgressBarVisible.value = true
                val response = sourcesRepository.getSources(categoryItem.categoryId,lang)
                //set progressBar bar false,when response has coming
                prgressBarVisible.value = false
                // to loop until size of sources
                sourcesLiveData.value = response
                Log.e("getSourceWithLanguage","response"+response)

            } catch (ex: Exception) {
                prgressBarVisible.value = false
                //log to know the error
                Log.e("onFailure", ex.localizedMessage)
            }
        }
    }
}