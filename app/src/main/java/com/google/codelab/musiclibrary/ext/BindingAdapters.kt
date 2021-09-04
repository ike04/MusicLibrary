package com.google.codelab.musiclibrary.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("url")
    fun WebView.loadUrlForBinding(url: String?) {
        if (url.isNullOrEmpty()) return

        loadUrl(url)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImageUrl(url: String?) {
        Glide.with(context)
            .load(url)
            .circleCrop()
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("visible")
    fun LottieAnimationView.visible(isVisible: Boolean){
        this.visibility =  if(isVisible){
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
