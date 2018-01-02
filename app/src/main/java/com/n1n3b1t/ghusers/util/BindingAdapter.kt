package com.n1n3b1t.ghusers.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.n1n3b1t.ghusers.R

/**
 * Created by valentintaranenko on 02/01/2018.
 */
object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view).load(url).apply(RequestOptions().apply { placeholder(R.drawable.placeholder) }).into(view)
    }

}