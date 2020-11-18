package com.gitalive.myapplication.ui.wiki

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.gitalive.myapplication.R
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    private lateinit var wikiViewModel: WikiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wikiViewModel = WikiViewModel(requireActivity().application)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_slideshow, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> wikiView.loadUrl(wikiViewModel.getWiki().value)
            R.id.action_back ->
                if (wikiView.canGoBack()) {
                    wikiView.goBack()
                }
            R.id.action_home -> wikiView.loadUrl(wikiViewModel.url)
            R.id.action_forward ->
                if (wikiView.canGoForward()) {
                    wikiView.goForward()
                }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        wikiView.settings.javaScriptEnabled = true
        wikiView.settings.setSupportZoom(true)
        wikiView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        wikiView.settings.loadsImagesAutomatically = true
        wikiView.settings.setSupportMultipleWindows(true)

        wikiView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                wikiViewModel.setWiki(url)
                view?.loadUrl(wikiViewModel.getWiki().value.toString())
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                wikiViewModel.setWiki(url)
                Toast.makeText(context, "页面加载中，请稍候", Toast.LENGTH_SHORT).show()
            }
        }
        wikiView.loadUrl(wikiViewModel.getWiki().value.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (wikiView.canGoBack()) {
                    wikiView.goBack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}
