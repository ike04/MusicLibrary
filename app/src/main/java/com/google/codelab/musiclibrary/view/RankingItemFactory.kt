package com.google.codelab.musiclibrary.view

import android.content.Context
import com.bumptech.glide.Glide
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.CellRankingSongBinding
import com.google.codelab.musiclibrary.model.ChartTracks
import com.xwray.groupie.databinding.BindableItem

class RankingItemFactory(private val song: ChartTracks, private val rank: Int, val context: Context, private val onShareClick: (Int) -> Unit) :
    BindableItem<CellRankingSongBinding>() {
    override fun getLayout(): Int = R.layout.cell_ranking_song

    override fun bind(viewBinding: CellRankingSongBinding, position: Int) {
        viewBinding.ranking.text = (rank + 1).toString()
        Glide.with(context).load(song.images?.coverart).into(viewBinding.imageView)
        viewBinding.artistName.text = song.subtitle
        viewBinding.songName.text = song.title

        viewBinding.share.setOnClickListener {
            onShareClick(position)
        }
    }
}
