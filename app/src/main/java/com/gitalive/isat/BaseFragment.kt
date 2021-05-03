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
import com.gitalive.isat.ui.FabWebView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_map.*

open class BaseFragment : Fragment() {
    private lateinit var wb: WebView
    private lateinit var shp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var isQuit = false
    lateinit var mUrl: String

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewConfig(webView: WebView) {
        wb = webView
        webView.apply {
            settings.apply {
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                javaScriptEnabled = true
                setSupportZoom(true)
                loadsImagesAutomatically = true
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    url?.let {
                        mUrl = it
                        view?.loadUrl(it)
                    }
                    return false
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
            shp.getString("url", mUrl)?.let { loadUrl(it) }
        }
    }

    private fun fabConfig(fab: FloatingActionButton, webView: WebView) {
        fab.apply {
            setOnClickListener {
                shp.getString("url", mUrl)?.let { webView.loadUrl(it) }
            }
            setOnLongClickListener {
                webView.loadUrl(mUrl)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (wb.canGoBack()) {
                        wb.goBack()
                    } else {
                        if (!isQuit) {
                            Toast.makeText(requireContext(), "再执行一次返回操作即可退出应用", Toast.LENGTH_SHORT)
                                .show()
                            isQuit = true
                        } else {
                            requireActivity().finish()
                        }
                    }
                }
            })
    }

    fun controllerConfig(fabWebView: FabWebView) {
        webViewConfig(fabWebView.webView)
        fabConfig(fabWebView.fab, fabWebView.webView)
    }

    protected fun setSPImpl(name: Int) {
        shp = requireActivity().getSharedPreferences(
            context?.resources?.getString(name),
            Context.MODE_PRIVATE
        )
        editor = shp.edit()
        editor.apply()
    }
}