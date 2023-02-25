package com.example.newsapp.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.ConstanttVariables
import com.example.newsapp.R
import com.example.newsapp.databinding.ArticleItemBinding


class DetailsArticleActivity : AppCompatActivity() {
    lateinit var details : TextView
    private lateinit var viewMore_btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_article)
        details = findViewById(R.id.detailsArticle_tv)
        var articleTitle = intent.getStringExtra(ConstanttVariables.ARTICLE_DESCRIPTION)
        details.setText(articleTitle.toString())
        var articleUrl = intent.getStringExtra(ConstanttVariables.ARTIICLE_URL)
        viewMore_btn = findViewById(R.id.viewMore_btn)
        viewMore_btn.setOnClickListener {
           startArticleUrl(articleUrl!!)
        }

    }

    private fun startArticleUrl(articleUrl : String) {
        val uri: Uri = Uri.parse(articleUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}