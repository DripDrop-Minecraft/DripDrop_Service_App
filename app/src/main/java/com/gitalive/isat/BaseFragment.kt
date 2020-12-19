package com.gitalive.isat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_map.*

open class BaseFragment : Fragment() {
    private lateinit var wb: WebView
    protected lateinit var androidViewModel: BaseAndroidViewModel

    @SuppressLint("SetJavaScriptEnabled")
    protected fun webViewConfig(webView: WebView) {
        wb = webView
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.loadsImagesAutomatically = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                url?.let { androidViewModel.setUrl(it) }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                Toast.makeText(context, "页面加载中，请稍候", Toast.LENGTH_SHORT).show()
            }
        }
        androidViewModel.getUrl()?.let { webView.loadUrl(it) }
    }

    private fun fabConfig(fab: FloatingActionButton) {
        fab.setOnClickListener {
            Toast.makeText(context, "页面已刷新", Toast.LENGTH_LONG).show()
            if (wb == mapwebView) {
                context?.cacheDir?.deleteRecursively()
                context?.externalCacheDir?.deleteRecursively()
            }
            androidViewModel.getUrl()?.let { wb.loadUrl(it) }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (wb.canGoBack()) {
                    wb.goBack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    protected fun viewModelConfig(name: Int, url: String) {
        androidViewModel.setSPImpl(name).setUrl(url)
    }

    protected fun controllers(webView: WebView, fab: FloatingActionButton) {
        webViewConfig(webView)
        fabConfig(fab)
    }
}