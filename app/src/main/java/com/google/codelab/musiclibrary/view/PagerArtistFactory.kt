package com.google.codelab.musiclibrary.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.CellSearchArtistBinding
import com.google.codelab.musiclibrary.model.ArtistsHit

class PagerArtistFactory(
    private val artist: List<ArtistsHit>,
    private val context: Context,
    private val onCellClick: (ArtistsHit) -> Unit
) :
    RecyclerView.Adapter<PagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CellSearchArtistBinding.inflate(layoutInflater, parent, false)
        return PagerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(artist[position], context)
        holder.itemView.setOnClickListener {
            onCellClick(artist[position])
        }
    }

    override fun getItemCount(): Int = artist.size
}

class PagerViewHolder(val binding: CellSearchArtistBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(artist: ArtistsHit, context: Context) {
        Glide.with(context).load(artist.artist.avatar).into(binding.pagerArtistImage)
        binding.pagerArtistName.text = artist.artist.name
    }
}
