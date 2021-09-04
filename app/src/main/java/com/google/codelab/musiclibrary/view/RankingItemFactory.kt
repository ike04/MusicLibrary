package com.google.codelab.musiclibrary.view

import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.CellRankingSongBinding
import com.google.codelab.musiclibrary.model.businessmodel.ChartBusinessModel
import com.xwray.groupie.databinding.BindableItem

class RankingItemFactory(
    private val song: ChartBusinessModel,
    private val rank: Int,
    private val onShareClick: (Int) -> Unit
) :
    BindableItem<CellRankingSongBinding>() {
    override fun getLayout(): Int = R.layout.cell_ranking_song

    override fun bind(viewBinding: CellRankingSongBinding, position: Int) {
        viewBinding.ranking.text = (rank + 1).toString()
        viewBinding.item = song

        viewBinding.share.setOnClickListener {
            onShareClick(position)
        }
    }
}
