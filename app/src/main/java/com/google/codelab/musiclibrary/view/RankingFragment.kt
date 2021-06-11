package com.google.codelab.musiclibrary.view

import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.FragmentRankingBinding
import com.google.codelab.musiclibrary.model.Song
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class RankingFragment : Fragment() {
    private lateinit var binding: FragmentRankingBinding
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val songList: MutableList<Song> = createSongTestData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRankingBinding.inflate(layoutInflater)

        requireActivity().setTitle(R.string.navigation_ranking)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rankingRecyclerView.adapter = groupAdapter
        groupAdapter.update(songList.map { RankingItemFactory(it){ position ->
            share(songList[position])
        } })
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
        fun createSongTestData(): MutableList<Song> {
            var i = 0
            songTestData.clear()
            while (i <= 20) {
                val song = Song(
                    rank = i + 1,
                    name = "kirari",
                    artist = "Fujii Kaze",
                    image = R.drawable.kirari
                )
                songTestData.add(song)
                i++
            }
            return songTestData
        }
    }
}
