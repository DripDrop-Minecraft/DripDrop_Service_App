package com.gitalive.isat.ui.wiki

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.webviewlayout.*

class SlideshowFragment : Fragment() {

    private lateinit var wikiViewModel: WikiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wikiViewModel = WikiViewModel(requireActivity().application)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_wiki, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                Toast.makeText(context, "页面加载中，请稍候", Toast.LENGTH_SHORT).show()
            }
        }
        webView.loadUrl(wikiViewModel.getWiki().value)
        fab.setOnClickListener {
            Toast.makeText(context, "页面已刷新", Toast.LENGTH_LONG).show()
            webView.loadUrl(wikiViewModel.getWiki().value)
        }
    }
}
