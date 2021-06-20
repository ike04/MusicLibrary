package com.google.codelab.musiclibrary.util

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.google.codelab.musiclibrary.model.ChartTracks
import com.google.codelab.musiclibrary.model.Song
import com.google.codelab.musiclibrary.model.Track

object ShareUtils {
    fun share(song: Track) : Intent{
        return Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${song.title}を共有する。\n(${song.url})")
            type = "text/plain"
        }
    }

    fun shareRanking(song: ChartTracks) : Intent{
        return Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${song.title}を共有する。\n(${song.url})")
            type = "text/plain"
        }
    }
}
