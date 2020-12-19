package com.gitalive.isat

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var shp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var mUrl: String

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
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                Toast.makeText(context, "页面加载中，请稍候", Toast.LENGTH_SHORT).show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                url?.let {
                    editor.putString("url", it)
                    editor.apply()
                }
            }
        }
        getUrl()?.let { webView.loadUrl(it) }
    }

    private fun fabConfig(fab: FloatingActionButton) {
        fab.setOnClickListener {
            Toast.makeText(context, "页面已刷新", Toast.LENGTH_SHORT).show()
            getUrl()?.let { wb.loadUrl(it) }
        }

        fab.setOnLongClickListener {
            context?.cacheDir?.deleteRecursively()
            context?.externalCacheDir?.deleteRecursively()
            Toast.makeText(context, "正在返回主页", Toast.LENGTH_SHORT).show()
            wb.loadUrl(mUrl)
            return@setOnLongClickListener true
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

    protected fun controllers(webView: WebView, fab: FloatingActionButton) {
        webViewConfig(webView)
        fabConfig(fab)
    }

    protected fun setSPImpl(name: Int) {
        shp = requireActivity().getSharedPreferences(
            context?.resources?.getString(name),
            Context.MODE_PRIVATE
        )
        editor = shp.edit()
        editor.apply()
    }

    protected fun setUrl(url: String) {
        mUrl = url
    }

    private fun getUrl(): String? {
        return shp.getString("url", mUrl)
    }
}