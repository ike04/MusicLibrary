package com.google.codelab.musiclibrary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.FragmentRankingBinding
import com.google.codelab.musiclibrary.ext.FragmentExt.showFragment
import com.google.codelab.musiclibrary.model.ChartResponse
import com.google.codelab.musiclibrary.model.ChartTracks
import com.google.codelab.musiclibrary.viewmodel.RankingViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnItemClickListener

class RankingFragment : Fragment() {
    private lateinit var binding: FragmentRankingBinding
    private lateinit var viewModel: RankingViewModel
    private val songList: MutableList<ChartTracks> = ArrayList()
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val onItemClickListener = OnItemClickListener { item, _ ->
        // どのitemがクリックされたかindexを取得
        val index = groupAdapter.getAdapterPosition(item)
        songList[index].url?.let { DetailWebViewFragment.newInstance(it).showFragment(parentFragmentManager) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRankingBinding.inflate(layoutInflater)
        viewModel = RankingViewModel()

        requireActivity().setTitle(R.string.navigation_ranking)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(songList.isEmpty()) {
            viewModel.fetchRankingMusic(0)
        }

        binding.rankingRecyclerView.adapter = groupAdapter

        viewModel.songList.observe(viewLifecycleOwner, { songs: List<ChartTracks> ->
            songs.map { songList.add(it) }
            groupAdapter.update(songList.mapIndexed { index, chartTracks ->
                RankingItemFactory(chartTracks, index, requireContext()) { position ->
//                    val sendIntent = ShareUtils.share(songList[position])
//                    val shareIntent = Intent.createChooser(sendIntent, null)
//                    startActivity(shareIntent)
                }
            })

        })

        groupAdapter.setOnItemClickListener(onItemClickListener)
    }
}
