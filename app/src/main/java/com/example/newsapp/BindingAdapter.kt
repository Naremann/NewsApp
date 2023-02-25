package com.example.newsapp

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imgUrl")
fun loadImgFromUrl(imageView : ImageView,urlImg : String?) {
    if (urlImg == null) {
        imageView.setImageResource(R.drawable.general)
    } else {
        Glide.with(imageView)
            .load(urlImg)
            .into(imageView)
    }
}

@BindingAdapter("imgDrawable")
fun getImgFromDrawable(imageView : ImageView,urlImg : Int) {
    imageView.setImageResource(urlImg)
}