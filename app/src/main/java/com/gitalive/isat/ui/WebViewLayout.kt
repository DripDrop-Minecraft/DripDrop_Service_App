package com.gitalive.isat.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebSettings
import androidx.constraintlayout.widget.ConstraintLayout
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.webviewlayout.view.*

@SuppressLint("SetJavaScriptEnabled")
class WebViewLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.webviewlayout, this)
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView.settings.setSupportZoom(true)
        webView.settings.loadsImagesAutomatically = true
    }
}