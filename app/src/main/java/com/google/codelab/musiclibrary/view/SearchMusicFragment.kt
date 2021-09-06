package com.google.codelab.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.FragmentSearchBinding
import com.google.codelab.musiclibrary.ext.FragmentExt.showFragment
import com.google.codelab.musiclibrary.model.*
import com.google.codelab.musiclibrary.model.businessmodel.Artists
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import com.google.codelab.musiclibrary.util.ShareUtils
import com.google.codelab.musiclibrary.viewmodel.SearchMusicViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMusicFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchMusicViewModel by viewModels()

    private val songs: MutableList<Tracks> = ArrayList()
    val artists: MutableList<Artists> = ArrayList()
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val onItemClickListener = OnItemClickListener { item, _ ->
        // どのitemがクリックされたかindexを取得
        val index = groupAdapter.getAdapterPosition(item)
        songs[index].url?.let { DetailWebViewFragment.newInstance(it).showFragment(parentFragmentManager) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBar.setOnQueryTextListener(
            SearchViewListener(
                viewModel,
                binding,
                songs,
                artists
            )
        )

        binding.recyclerView.searchResultRecyclerView.adapter = groupAdapter
        binding.viewPager.searchResultArtist.adapter =
            PagerArtistFactory(artists) { artist ->
                artist.avatar?.let {
                    ArtistDetailFragment.newInstance(artist.id, it)
                        .showFragment(parentFragmentManager)
                }
            }

        viewModel.artistlist.observe(viewLifecycleOwner, { artistList: List<Artists> ->
            artistList.map { artists.add(it) }
            binding.noNetwork = false
            binding.viewPager.searchResultArtist.adapter?.notifyDataSetChanged()
        })

        viewModel.songList.observe(viewLifecycleOwner, { musicList: List<Tracks> ->
            musicList.map { songs.add(it) }
            binding.noNetwork = false
            groupAdapter.update(songs.map {
                SearchItemFactory(it, requireContext()) { position ->
                    val sendIntent = ShareUtils.share(songs[position])
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
            })
        })

        viewModel.errorStream.observe(viewLifecycleOwner, { failure ->
            Snackbar.make(view, failure.message, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry) {
                    binding.noNetwork = false
                    binding.searchBar.query?.let { viewModel.fetchMusic(it.toString(), 0) }
                }.show()
            when (failure) {
                FailureType.NetworkError -> {
                    binding.noNetwork = true
                }
                else -> {
                }
            }
        })

        groupAdapter.setOnItemClickListener(onItemClickListener)
    }

    class SearchViewListener(
        private val viewModel: SearchMusicViewModel,
        private val binding: FragmentSearchBinding,
        private val songs: MutableList<Tracks>,
        private val artists: MutableList<Artists>
    ) : SearchView.OnQueryTextListener {
        // 文字が入力されたタイミングで実行される
        override fun onQueryTextChange(newText: String?): Boolean {
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
}
