package com.google.codelab.musiclibrary.ext

import android.graphics.drawable.Drawable
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
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
}
