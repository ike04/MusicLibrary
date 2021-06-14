package com.google.codelab.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.codelab.musiclibrary.databinding.FragmentArtistDetailBinding
import com.google.codelab.musiclibrary.model.Song
import com.google.codelab.musiclibrary.util.ShareUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ArtistDetailFragment : Fragment() {
    private lateinit var binding: FragmentArtistDetailBinding
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val songList: MutableList<Song> = SearchMusicFragment.createSongTestData()
    private val artistId: String
        get() = checkNotNull(arguments?.getString(ARTIST_ID))

    private val image: String
        get() = checkNotNull(arguments?.getString(IMAGE))

    companion object {
        private const val ARTIST_ID = "artistId"
        private const val IMAGE = "image"
        fun newInstance(artistId: String, image :String): ArtistDetailFragment {
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
        Glide.with(requireContext()).load(image).into(binding.artistImage)
        binding.artistSongRecyclerView.adapter = groupAdapter
//        groupAdapter.update(songList.map{ArtistDetailItemFactory(it){ position ->
//            val sendIntent = ShareUtils.share(songList[position])
//            val shareIntent = Intent.createChooser(sendIntent, null)
//            startActivity(shareIntent)
//
//        } })
    }
}
