package com.gitalive.myapplication.ui.bbs

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.gitalive.myapplication.R
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    private lateinit var bbsViewModel: BBSViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bbsViewModel = BBSViewModel(requireActivity().application)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> bbsView.loadUrl(bbsViewModel.getBBS().value)
            R.id.action_back ->
                if (bbsView.canGoBack()) {
                    bbsView.goBack()
                }
            R.id.action_home -> bbsView.loadUrl(bbsViewModel.url)
            R.id.action_forward ->
                if (bbsView.canGoForward()) {
                    bbsView.goForward()
                }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bbsView.settings.javaScriptEnabled = true
        bbsView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        bbsView.setInitialScale(100)
        bbsView.settings.textZoom = 130
        bbsView.settings.setSupportZoom(true)
        bbsView.settings.loadsImagesAutomatically = true

        bbsView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(bbsViewModel.getBBS().value.toString())
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                bbsViewModel.setBBS(url)
            }
        }
        bbsView.loadUrl(bbsViewModel.getBBS().value.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (bbsView.canGoBack()) {
                    bbsView.goBack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}
