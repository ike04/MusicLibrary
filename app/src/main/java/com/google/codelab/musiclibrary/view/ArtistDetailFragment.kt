package com.google.codelab.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.FragmentArtistDetailBinding
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import com.google.codelab.musiclibrary.util.ShareUtils
import com.google.codelab.musiclibrary.viewmodel.ArtistDetailViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {
    private lateinit var binding: FragmentArtistDetailBinding
    private val viewModel: ArtistDetailViewModel by viewModels()
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val songList: MutableList<Tracks> = ArrayList()
    private val artistId: String
        get() = checkNotNull(arguments?.getString(ARTIST_ID))

    private val image: String
        get() = checkNotNull(arguments?.getString(IMAGE))

    companion object {
        private const val ARTIST_ID = "artistId"
        private const val IMAGE = "image"
        fun newInstance(artistId: String, image: String): ArtistDetailFragment {
            return ArtistDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARTIST_ID, artistId)
                    putString(IMAGE, image)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentArtistDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        Glide.with(requireContext()).load(image).into(binding.artistImage)

        viewModel.fetchArtistTracks(artistId)

        binding.artistSongRecyclerView.adapter = groupAdapter

        viewModel.artistTracks.observe(viewLifecycleOwner, { topSongs ->
            topSongs.map { songList.add(it) }
            groupAdapter.update(songList.map {
                ArtistDetailItemFactory(it, requireContext()) { position ->
                    val sendIntent = ShareUtils.share(songList[position])
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
            })
        })

        viewModel.errorStream.observe(viewLifecycleOwner, { failure ->
            Snackbar.make(view, failure.message, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry) {
                    viewModel.fetchArtistTracks(artistId)
                }.show()
        })
    }
}
