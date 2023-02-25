package com.example.newsapp.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.LanguagesSettingsHelper
import com.example.newsapp.LocaleHelper
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.ui.fragments.CategoryFragment
import com.example.newsapp.ui.news.NewsFragment
import com.example.newsapp.ui.fragments.SettingsFragment
import com.example.newsapp.model.CategoryItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    var settingFragment : SettingsFragment = SettingsFragment()
    var categoryFragment : CategoryFragment = CategoryFragment()
    lateinit var viewBinding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocalLanguage()
        pushFragment(categoryFragment)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        categoryFragment.onCategoryClick = object : CategoryFragment.OnCategoryClickListener {
            override fun onItemClick(categoryItem: CategoryItem, position: Int) {
                viewBinding.appBarHome.newsAppTv.text = categoryItem.categoryTitle
                pushFragment(NewsFragment.getInstance(categoryItem))
            }

        }
        initViews()
    }

    private fun setLocalLanguage() {
        var data = LanguagesSettingsHelper.retreiveDataFromSharedPreferences("lang",this)
        if(data == "ar"){
            LocaleHelper.setLocale(this,"ar")
        }else{
            Log.e("lang" , data.toString())
            LocaleHelper.setLocale(this,"en")

        }
    }

    private fun initViews() {
        viewBinding.appBarHome.icMenuImg.setOnClickListener {
            viewBinding.drawerLayout.open()
        }
        viewBinding.categoriesLinearLatout.setOnClickListener {
            pushFragment(categoryFragment)

           viewBinding.drawerLayout.close()
        }
        viewBinding.settingLinearLatout.setOnClickListener {
            pushFragment(settingFragment)
            viewBinding.drawerLayout.close()
        }

    }

    private fun pushFragment(fragment : Fragment,tag:String?=null) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment,tag).addToBackStack(null).commit()
    }



}