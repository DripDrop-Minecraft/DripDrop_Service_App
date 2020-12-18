package com.gitalive.isat.ui.wiki

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gitalive.isat.BaseFragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.fragment_wiki.*

class WikiFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wiki, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = "https://minecraft-zh.gamepedia.com/Minecraft_Wiki"
        webViewConfig(wikiwebView, url)
        fabConfig(wikifab, url, wikiwebView)
    }
}
