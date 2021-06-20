package com.google.codelab.musiclibrary.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.ActivityMainBinding
import com.google.codelab.musiclibrary.ext.FragmentExt.showFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val searchFragment = SearchMusicFragment()
    private val rankingFragment = RankingFragment()

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> {
                    searchFragment.showFragment(supportFragmentManager)
                    supportActionBar?.hide()
                    true
                }
                R.id.navigation_ranking -> {
                    rankingFragment.showFragment(supportFragmentManager)
                    supportActionBar?.show()
                    true
                }
                else -> false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        searchFragment.showFragment(supportFragmentManager)
        supportActionBar?.hide()

        binding.navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        setContentView(binding.root)
    }
}
