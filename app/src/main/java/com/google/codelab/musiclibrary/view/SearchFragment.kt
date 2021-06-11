package com.google.codelab.musiclibrary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.FragmentSearchBinding
import com.google.codelab.musiclibrary.model.Song
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

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

        groupAdapter.update(createTestData().map { SearchItemFactory(it) })
    }

    companion object {
        private val testData: MutableList<Song> = ArrayList()
        fun createTestData(): MutableList<Song> {
            var i = 0
            testData.clear()
            while (i <= 20) {
                val song = Song(
                    name = "kirari",
                    artist = "Fujii Kaze",
                    image = R.drawable.kirari
                )
                testData.add(song)
                i++
            }
            return testData
        }
    }

}
