package com.gitalive.isat.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.gitalive.isat.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabWebView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    val webView: WebView by lazy {
        findViewById(R.id.webview)
    }
    val fab: FloatingActionButton by lazy {
        findViewById(R.id.fab)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.fragment_webview, this)
        fab.apply {
            setOnClickListener {
                Toast.makeText(context, "页面已刷新", Toast.LENGTH_SHORT).show()
            }
            setOnLongClickListener {
                context.cacheDir?.deleteRecursively()
                context.externalCacheDir?.deleteRecursively()
                Toast.makeText(context, "正在返回主页", Toast.LENGTH_SHORT).show()
                return@setOnLongClickListener true
            }
        }
    }
}