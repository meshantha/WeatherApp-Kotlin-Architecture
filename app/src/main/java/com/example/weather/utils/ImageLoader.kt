package com.example.weather.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {
    fun loadImageUrl(iv: ImageView, url: String) {
        Glide.with(iv).load(url).into(iv)
    }
}