package com.example.newsapp.ui.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.ConstanttVariables
import com.example.newsapp.R
import com.example.newsapp.adapter.ArticleItemAdapter
import com.example.newsapp.api.ApiManager
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.model.*
import com.example.newsapp.ui.activities.DetailsArticleActivity
import com.example.newsapp.ui.activities.HomeActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class NewsFragment : Fragment() {
    var articleItemAdapter: ArticleItemAdapter = ArticleItemAdapter()
    lateinit var categoryItem: CategoryItem
    lateinit var viewModel: NewsViewModel
    var searchView: androidx.appcompat.widget.SearchView? = null
    lateinit var source: SourcesItem
    lateinit var viewDataBinding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        subscribeToLiveData()
        viewModel.getSources(requireContext(), categoryItem)
        articleItemAdapter.onTitleItemClick = object : ArticleItemAdapter.OnArticleTitleListener {
            override fun OnItemClick(article: ArticlesItem) {
                startDetailsActivity(article)
            }
        }
    }

    private fun subscribeToLiveData() {
        viewModel.prgressBarVisible.observe(viewLifecycleOwner, object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                viewDataBinding.progressBar.isVisible = t!!
            }
        })
        viewModel.sourcesLiveData.observe(
            viewLifecycleOwner,
            object : Observer<List<SourcesItem?>?> {
                override fun onChanged(t: List<SourcesItem?>?) {
                    showResponsesInTabs(t)
                }

            }


        )
        viewModel.newsList.observe(viewLifecycleOwner, object : Observer<List<ArticlesItem?>?> {
            override fun onChanged(t: List<ArticlesItem?>?) {
                articleItemAdapter.articlesList = t!!
                articleItemAdapter.notifyDataSetChanged()
            }
        })
    }


    private fun showResponsesInTabs(response: List<SourcesItem?>?) {
        for (i in 0 until response!!.size) {
            val tab = viewDataBinding.tabLayout.newTab()
            tab.tag = response[i]
            tab.text = response[i]!!.name
            viewDataBinding.tabLayout.addTab(tab)
            setTabEndMargin()
        }
    }

    private fun setTabEndMargin() {
        val tabs = viewDataBinding.tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabs.childCount) {
            val tab = tabs.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginEnd = 25
            tab.layoutParams = layoutParams
            viewDataBinding.tabLayout.requestLayout()
        }
    }

    private fun startDetailsActivity(article: ArticlesItem) {
        var intent = Intent(context, DetailsArticleActivity::class.java)
        intent.putExtra(ConstanttVariables.ARTICLE_DESCRIPTION, article.description)
        intent.putExtra(ConstanttVariables.ARTIICLE_URL, article.url)
        startActivity(intent)
    }

    private fun initViews() {
        searchView = (this.activity as HomeActivity?)!!.findViewById(R.id.searchView)
        viewDataBinding.recyclerViewArticles.adapter = articleItemAdapter
        viewDataBinding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                source = tab!!.tag as SourcesItem
                viewModel.loadTabArticles(requireContext(), Source(source.name, source.id), 1)
                viewDataBinding.recyclerViewArticles.layoutManager!!.scrollToPosition(0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                source = tab!!.tag as SourcesItem
                viewModel.loadTabArticles(requireContext(), Source(source.name, source.id), 1)
                viewDataBinding.recyclerViewArticles.layoutManager!!.scrollToPosition(0)
            }
        })
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                ApiManager.getApis().searchArticles(
                    ConstanttVariables.API_KEY, source.id!!, query!!
                ).enqueue(object : Callback<ArticleResponses> {
                    override fun onResponse(
                        call: Call<ArticleResponses>,
                        response: Response<ArticleResponses>
                    ) {
                        articleItemAdapter.articlesList = response.body()!!.articles
                        articleItemAdapter.notifyDataSetChanged()

                    }

                    override fun onFailure(call: Call<ArticleResponses>, t: Throwable) {
                        Log.e("search article", "Error" + t.localizedMessage)
                    }

                })
                return true;
            }

            override fun onQueryTextChange(query: String?): Boolean {
                //searchView!!.setBackground(resources.getDrawable(R.drawable.rounded_img));
                ApiManager.getApis().searchArticles(
                    ConstanttVariables.API_KEY, source.id!!, query!!
                ).enqueue(object : Callback<ArticleResponses> {
                    override fun onResponse(
                        call: Call<ArticleResponses>,
                        response: Response<ArticleResponses>
                    ) {
                        articleItemAdapter.articlesList = response.body()!!.articles
                        articleItemAdapter.notifyDataSetChanged()

                    }

                    override fun onFailure(call: Call<ArticleResponses>, t: Throwable) {
                        Log.e("search article", "Error" + t.localizedMessage)
                    }

                })
                return true;
            }
        })
        articleItemAdapter.onPageClick = object : ArticleItemAdapter.OnPageClickListener {
            override fun onPageClick(pageId: Int, linearLayout: LinearLayout) {
                viewModel.loadTabArticles(
                    requireContext(),
                    Source(source.name, source.id),
                    (pageId + 1)
                )
                viewDataBinding.recyclerViewArticles.layoutManager!!.scrollToPosition(0)
                Log.e("page", "page num:" + (pageId + 1))

            }
        }
    }

    companion object {
        fun getInstance(categoryItem: CategoryItem): NewsFragment {
            val fragment = NewsFragment()
            fragment.categoryItem = categoryItem
            return fragment

        }
    }
}