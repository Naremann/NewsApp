package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.CategoryLeftItemBinding
import com.example.newsapp.databinding.CategoryRightItemBinding
import com.example.newsapp.model.CategoryItem

class CategoryItemAdapter(var categoryList: List<CategoryItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val leftItemCode = 0
    private val rightItemCode = 1


    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) leftItemCode else rightItemCode
    }



    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBindingLeft: CategoryLeftItemBinding
        val viewBindingRight: CategoryRightItemBinding
        if (viewType == leftItemCode) {
            viewBindingLeft = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.category_left_item,
                parent,
                false)
            return CategoryLeftItemViewHolder(viewBindingLeft)
        } else {
            viewBindingRight = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.category_right_item,
                parent,
                false
            )
            return CategoryRightItemViewHolder(viewBindingRight)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val categoryItem = categoryList[position]
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(categoryItem, position)
        }

        if (getItemViewType(position) == rightItemCode) {
            (holder as CategoryRightItemViewHolder).bind(categoryItem)
            holder.rightItemBinding.materialCardView.setCardBackgroundColor(getColor(holder.itemView.context, categoryItem.catagoryBackground))

        } else {
            (holder as CategoryLeftItemViewHolder).bind(categoryItem)
            holder.leftItemBinding.materialCardView.setCardBackgroundColor(
                getColor(
                    holder.itemView.context,
                    categoryItem.catagoryBackground))
        }


    }
    lateinit var onItemClick: OnCategoryItemClickListener

    interface OnCategoryItemClickListener {
        fun onItemClick(categoryItem: CategoryItem, position: Int)
    }





    class CategoryLeftItemViewHolder(val leftItemBinding: CategoryLeftItemBinding) : RecyclerView.ViewHolder(leftItemBinding.root) {
        fun bind(categoryItem: CategoryItem) {
            leftItemBinding.item = categoryItem
            leftItemBinding.invalidateAll()
        }
    }
    class CategoryRightItemViewHolder(val rightItemBinding: CategoryRightItemBinding) : RecyclerView.ViewHolder(rightItemBinding.root) {
        fun bind(categoryItem: CategoryItem) {
            rightItemBinding.item = categoryItem
            rightItemBinding.invalidateAll()

        }
    }
}