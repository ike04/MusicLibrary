package com.google.codelab.musiclibrary.util

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.google.codelab.musiclibrary.model.Song

object ShareUtils {
    fun share(song: Song) : Intent{
        return Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${song.name}を共有する。")
            type = "text/plain"
        }
    }
}
