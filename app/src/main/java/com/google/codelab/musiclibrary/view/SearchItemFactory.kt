package com.google.codelab.musiclibrary.view

import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.Song
import com.google.codelab.musiclibrary.databinding.CellSearchSongBinding
import com.xwray.groupie.databinding.BindableItem

class SearchItemFactory(private val song: Song): BindableItem<CellSearchSongBinding>() {
    override fun getLayout(): Int = R.layout.cell_search_song

    override fun bind(viewBinding: CellSearchSongBinding, position: Int) {
        viewBinding.imageView.setImageResource(song.image)
        viewBinding.artistName.text = song.artist
        viewBinding.songName.text = song.name
    }
}
