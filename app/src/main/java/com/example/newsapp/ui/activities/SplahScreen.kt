package com.example.newsapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.newsapp.R

class SplahScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splah_screen)
        Handler(Looper.getMainLooper()).postDelayed({ startHomeActivity() },2000)
    }

    private fun startHomeActivity() {
        val intent : Intent = Intent(this, HomeActivity :: class.java)
        startActivity(intent)
        finish()
    }
}