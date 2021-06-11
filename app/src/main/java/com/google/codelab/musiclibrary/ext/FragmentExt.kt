package com.google.codelab.musiclibrary.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.codelab.musiclibrary.R

object FragmentExt {
    fun Fragment.showFragment(fragmentManager: FragmentManager){
        fragmentManager.beginTransaction()
            .replace(R.id.content_view,this)
            .commit()
    }
}
