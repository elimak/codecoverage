package com.elimak.navigationdemo.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageRes")
fun setImageRes(imageView: ImageView, res: Int?) {
    if(res != null) {
        imageView.setImageResource(res)
        imageView.visibility = View.VISIBLE
    } else {
        imageView.visibility = View.GONE
    }
}
