package com.example.newsapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.newsapp.database.MyDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.init(context = this)
        val networkStatusHandler = object : NetworkStatusHandler{
            override fun isOnline(): Boolean {
                return isNetworkAvailable()
            }

        }
        ConstanttVariables.networkStatusHandler = networkStatusHandler
    }

    private fun isNetworkAvailable() : Boolean {
        var connected = false
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
         if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED)
             connected = true
        return connected
    }
}