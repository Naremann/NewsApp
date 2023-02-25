package com.example.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.CategoryItemAdapter
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.model.CategoryItem

class CategoryFragment : Fragment() {
    lateinit var categoryItemAdapter: CategoryItemAdapter
    var categoryList: MutableList<CategoryItem> = mutableListOf()
    lateinit var viewBinding : FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_category, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(categoryList.isEmpty()){
        getCategories()}
        initViews()
        categoryItemAdapter.onItemClick = object : CategoryItemAdapter.OnCategoryItemClickListener{
            override fun onItemClick(categoryItem: CategoryItem, position: Int) {
                onCategoryClick.onItemClick(categoryItem,position)
            }
        }
    }
    lateinit var onCategoryClick : OnCategoryClickListener
    interface OnCategoryClickListener{
        fun onItemClick(categoryItem: CategoryItem,position: Int)
    }

    private fun getCategories() {

        categoryList.add(
            CategoryItem(
                R.drawable.sports, resources.getString(R.string.sports), R.color.red, "sports"
            )
        )
        categoryList.add(
            CategoryItem(
                R.drawable.general,
                resources.getString(R.string.General),
                R.color.purple_700,
                "general"
            )
        )
        categoryList.add(
            CategoryItem(
                R.drawable.health, resources.getString(R.string.Health), R.color.pink, "health"
            )
        )
        categoryList.add(
            CategoryItem(
                R.drawable.business,
                resources.getString(R.string.Business),
                R.color.brown,
                "business"
            )
        )
        categoryList.add(
            CategoryItem(
                R.drawable.entertainment,
                resources.getString(R.string.Entertainment),
                R.color.light_blue,
                "entertainment"
            )
        )
        categoryList.add(
            CategoryItem(
                R.drawable.science, resources.getString(R.string.Science), R.color.yellow, "science"
            )
        )
        categoryList.add(
            CategoryItem(
                R.drawable.technology,
                resources.getString(R.string.Technology),
                R.color.dark_blue,
                "technology"
            )
        )
    }

    private fun initViews() {
        categoryItemAdapter = CategoryItemAdapter(categoryList)
        viewBinding.recyclerCategories.adapter = categoryItemAdapter
    }

}