package com.google.codelab.musiclibrary.util

import android.content.Intent
import com.google.codelab.musiclibrary.model.businessmodel.ChartBusinessModel
import com.google.codelab.musiclibrary.model.Track
import com.google.codelab.musiclibrary.model.businessmodel.Tracks

object ShareUtils {
    fun share(song: Tracks) : Intent{
        return Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${song.title}を共有する。\n(${song.url})")
            type = "text/plain"
        }
    }

    fun shareRanking(song: ChartBusinessModel) : Intent{
        return Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${song.title}を共有する。\n(${song.url})")
            type = "text/plain"
        }
    }
}
