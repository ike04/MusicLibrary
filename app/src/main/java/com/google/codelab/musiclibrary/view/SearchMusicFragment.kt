package com.google.codelab.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.FragmentSearchBinding
import com.google.codelab.musiclibrary.ext.FragmentExt.showFragment
import com.google.codelab.musiclibrary.model.*
import com.google.codelab.musiclibrary.util.ShareUtils
import com.google.codelab.musiclibrary.viewmodel.SearchMusicViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnItemClickListener

class SearchMusicFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchMusicViewModel
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val onItemClickListener = OnItemClickListener { item, _ ->
        // どのitemがクリックされたかindexを取得
        val index = groupAdapter.getAdapterPosition(item)
        DetailWebViewFragment.newInstance(songs[index].url).showFragment(parentFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(layoutInflater)
        viewModel = SearchMusicViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBar.setOnQueryTextListener(SearchViewListener(viewModel))

        binding.recyclerView.searchResultRecyclerView.adapter = groupAdapter
        binding.viewPager.searchResultArtist.adapter =
            PagerArtistFactory(artists, requireContext()) { artist ->
                ArtistDetailFragment.newInstance(artist.artist.id, artist.artist.avatar)
                    .showFragment(parentFragmentManager)
            }

        viewModel.artistlist.observe(viewLifecycleOwner, { artistList: Artists ->
            artistList.hits.map { artists.add(it) }
            binding.viewPager.searchResultArtist.adapter?.notifyDataSetChanged()
        })

        viewModel.songList.observe(viewLifecycleOwner, { musicList: Tracks ->
            musicList.hits.map { songs.add(it.track) }
            groupAdapter.update(songs.map {
                SearchItemFactory(it, requireContext()) { position ->
                    val sendIntent = ShareUtils.share(songs[position])
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
            })
        })

        groupAdapter.setOnItemClickListener(onItemClickListener)
    }

    class SearchViewListener(
        private val viewModel: SearchMusicViewModel
    ) : SearchView.OnQueryTextListener {
        // 文字が入力されたタイミングで実行される
        override fun onQueryTextChange(newText: String?): Boolean {
//            viewModel.searchArticles(newText)
            return false
        }

        // 検索が実行されたタイミングで実行される
        override fun onQueryTextSubmit(query: String?): Boolean {
            songs.clear()
            artists.clear()
            query?.let { viewModel.fetchMusic(it, 0) }
            return false
        }
    }


    companion object {
        val songs: MutableList<Track> = ArrayList()
        val artists: MutableList<ArtistsHit> = ArrayList()
        private val songTestData: MutableList<Song> = ArrayList()

        fun createSongTestData(): MutableList<Song> {
            var i = 0
            songTestData.clear()
            while (i <= 20) {
                val song = Song(
                    rank = null,
                    name = "kirari",
                    artist = "Fujii Kaze",
                    image = R.drawable.kirari,
                    url = "https://www.shazam.com/track/567600879/kirari"
                )
                songTestData.add(song)
                i++
            }
            return songTestData
        }
    }

}
