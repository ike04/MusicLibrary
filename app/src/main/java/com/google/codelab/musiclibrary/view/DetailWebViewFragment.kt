package com.google.codelab.musiclibrary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.codelab.musiclibrary.databinding.FragmentDetailWebViewBinding

class DetailWebViewFragment : Fragment() {
    private lateinit var binding: FragmentDetailWebViewBinding
    private val url: String
        get() = checkNotNull(arguments?.getString(URL))

    companion object {
        private const val URL = "url"
        fun newInstance(url: String): DetailWebViewFragment {
            return DetailWebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailWebViewBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.webView.loadUrl(url)
        return binding.root
    }

}
