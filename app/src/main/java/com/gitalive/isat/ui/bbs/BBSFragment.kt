package com.gitalive.isat.ui.bbs

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.fragment_bbs.*

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bbs, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = "http://www.mcbbs.net/"
        bbswebView.settings.javaScriptEnabled = true
        bbswebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        bbswebView.settings.setSupportZoom(true)
        bbswebView.settings.loadsImagesAutomatically = true
        bbswebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                Toast.makeText(context, "页面加载中，请稍候", Toast.LENGTH_LONG).show()
            }
        }
        bbswebView.loadUrl(url)
        bbsfab.setOnClickListener {
            Toast.makeText(context, "页面已刷新", Toast.LENGTH_LONG).show()
            bbswebView.loadUrl(url)
        }
    }
}
