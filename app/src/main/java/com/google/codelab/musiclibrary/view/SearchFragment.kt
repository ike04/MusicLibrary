package com.google.codelab.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.FragmentSearchBinding
import com.google.codelab.musiclibrary.model.Artist
import com.google.codelab.musiclibrary.model.Song
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnItemClickListener

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val songList: MutableList<Song> = createSongTestData()
    private val onItemClickListener = OnItemClickListener { item, _ ->
        // どのitemがクリックされたかindexを取得
        val index = groupAdapter.getAdapterPosition(item)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.searchResultRecyclerView.adapter = groupAdapter
        binding.viewPager.searchResultArtist.adapter =
            PagerArtistFactory(createArtistTestData().map { it }) {
                // ToDo: 画面遷移の処理をかく
            }

        groupAdapter.update(songList.map { SearchItemFactory(it) { position ->
            share(songList[position])
        } })
        groupAdapter.setOnItemClickListener(onItemClickListener)
    }

    fun share(song: Song){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${song.name}を共有する。")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    companion object {
        private val songTestData: MutableList<Song> = ArrayList()
        private val artistTestData: MutableList<Artist> = ArrayList()
        fun createSongTestData(): MutableList<Song> {
            var i = 0
            songTestData.clear()
            while (i <= 20) {
                val song = Song(
                    rank = null,
                    name = "kirari",
                    artist = "Fujii Kaze",
                    image = R.drawable.kirari
                )
                songTestData.add(song)
                i++
            }
            return songTestData
        }

        fun createArtistTestData(): MutableList<Artist> {
            var j = 0
            artistTestData.clear()
            while (j <= 4) {
                val artist = Artist(
                    name = "Aimyon",
                    image = R.drawable.kirari
                )
                artistTestData.add(artist)
                j++
            }
            return artistTestData
        }
    }

}
