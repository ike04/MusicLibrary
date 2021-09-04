package com.google.codelab.musiclibrary.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.codelab.musiclibrary.databinding.CellSearchArtistBinding
import com.google.codelab.musiclibrary.model.ArtistsHit
import com.google.codelab.musiclibrary.model.businessmodel.Artists

class PagerArtistFactory(
    private val artist: List<Artists>,
    private val onCellClick: (Artists) -> Unit
) :
    RecyclerView.Adapter<PagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CellSearchArtistBinding.inflate(layoutInflater, parent, false)
        return PagerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(artist[position])
        holder.itemView.setOnClickListener {
            onCellClick(artist[position])
        }
    }

    override fun getItemCount(): Int = artist.size
}

class PagerViewHolder(val binding: CellSearchArtistBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(artist: Artists) {
        binding.item = artist
    }
}
