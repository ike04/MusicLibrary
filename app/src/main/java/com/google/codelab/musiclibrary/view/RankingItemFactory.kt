package com.google.codelab.musiclibrary.view

import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.CellRankingSongBinding
import com.google.codelab.musiclibrary.model.Song
import com.xwray.groupie.databinding.BindableItem

class RankingItemFactory(private val song: Song, private val onShareClick: (Int) -> Unit) :
    BindableItem<CellRankingSongBinding>() {
    override fun getLayout(): Int = R.layout.cell_ranking_song

    override fun bind(viewBinding: CellRankingSongBinding, position: Int) {
        viewBinding.ranking.text = song.rank.toString()
        viewBinding.imageView.setImageResource(song.image)
        viewBinding.artistName.text = song.artist
        viewBinding.songName.text = song.name

        viewBinding.share.setOnClickListener {
            onShareClick(position)
        }
    }
}
