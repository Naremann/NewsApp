package com.example.newsapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkStatusHandlerImp(val context: Context) : NetworkStatusHandler {
    override fun isOnline(): Boolean {
        return isNetworkAvailable()
    }
    private fun isNetworkAvailable() : Boolean {
        var connected = false
        val connectivityManager = context.getSystemService(Application.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED)
            connected = true
        return connected
    }
}