package com.gitalive.isat.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val url = "http://neverlag.gitee.io/minecraft-wiki/#/"
        webViewConfig(webwebView, url)
        fabConfig(webfab, url, webwebView)
    }
}
