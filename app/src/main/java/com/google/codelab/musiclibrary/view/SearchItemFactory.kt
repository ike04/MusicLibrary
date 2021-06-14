package com.google.codelab.musiclibrary.view

import android.content.Context
import com.bumptech.glide.Glide
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.CellSearchSongBinding
import com.google.codelab.musiclibrary.model.Song
import com.google.codelab.musiclibrary.model.Track
import com.xwray.groupie.databinding.BindableItem

class SearchItemFactory(private val song: Track, private val context: Context, private val onShareClick: (Int) -> Unit) :
    BindableItem<CellSearchSongBinding>() {
    override fun getLayout(): Int = R.layout.cell_search_song

    override fun bind(viewBinding: CellSearchSongBinding, position: Int) {
        Glide.with(context).load(song.images.coverart).into(viewBinding.imageView)
        viewBinding.artistName.text = song.subtitle
        viewBinding.songName.text = song.title

        viewBinding.share.setOnClickListener {
            onShareClick(position)
        }
    }
}
