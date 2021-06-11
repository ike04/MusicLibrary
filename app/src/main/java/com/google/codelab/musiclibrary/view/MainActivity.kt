package com.google.codelab.musiclibrary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.codelab.musiclibrary.R
import com.google.codelab.musiclibrary.databinding.ActivityMainBinding
import com.google.codelab.musiclibrary.ext.FragmentExt.showFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val searchFragment = SearchFragment()
    private val rankingFragment= RankingFragment()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                searchFragment.showFragment(supportFragmentManager)
                true
            }
            R.id.navigation_ranking -> {
                rankingFragment.showFragment(supportFragmentManager)
                true
            }
            else -> false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        searchFragment.showFragment(supportFragmentManager)

        binding.navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        setContentView(binding.root)
    }
}
