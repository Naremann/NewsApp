package com.example.newsapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ArticleItemBinding
import com.example.newsapp.model.ArticlesItem

class ArticleItemAdapter(var articlesList : List<ArticlesItem?>? = null) : RecyclerView.Adapter<ArticleItemAdapter.ArticleItemViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        //var view = LayoutInflater.from(parent.context).inflate(R.layout.article_item,parent,false)
        //return ArticleItemViewHolder(view)
        val viewBinding : ArticleItemBinding
        = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.article_item,parent,false)
        return ArticleItemViewHolder(viewBinding)
    }


    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        var articleItem = articlesList?.get(position)
        //holder.articleImg.setImageResource()
        holder.bind(articleItem!!)

        holder.itemBinding.linearLayout.setVisibility(if (position === (articlesList?.size)!! - 1) View.VISIBLE else View.GONE)
        holder.itemBinding.articleTitleTv.setOnClickListener {
            onTitleItemClick!!.OnItemClick(articleItem!!)
        }

        val count = holder.itemBinding.linearLayout.childCount
        var view: View? = null
        for (i in 0 until count) {
            view = holder.itemBinding.linearLayout.getChildAt(i)
            //do something with your child element
            (view as TextView).setOnClickListener {
                if (view is TextView) {
                    (view as TextView).setTextColor(Color.BLUE)
                }
                onPageClick?.onPageClick(i,holder.itemBinding.linearLayout)


            }

        }


    }


    override fun getItemCount(): Int {
        //return if(articlesList== null) 0 else articlesList!!.size
        return articlesList?.size ?:0
    }

    var onPageClick : OnPageClickListener? = null
    interface OnPageClickListener{
        fun onPageClick(pageId:Int,linearLayout: LinearLayout)
    }

    var onTitleItemClick : OnArticleTitleListener? = null
    interface OnArticleTitleListener{
        fun OnItemClick(article: ArticlesItem)
    }

    class ArticleItemViewHolder(val itemBinding : ArticleItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ArticlesItem){
            itemBinding.item = item
            itemBinding.invalidateAll()
        }

    }
}