package com.gitalive.isat.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = "http://txyminecraft.club:8123/"
        mapwebView.settings.javaScriptEnabled = true
        mapwebView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        mapwebView.settings.setSupportZoom(true)
        mapwebView.settings.loadsImagesAutomatically = true
        mapwebView.setInitialScale(100)
        mapwebView.settings.textZoom = 200
        mapwebView.loadUrl(url)
        mapfab.setOnClickListener {
            Toast.makeText(context, "页面已刷新", Toast.LENGTH_LONG).show()
            mapwebView.loadUrl(url)
        }
    }
}
