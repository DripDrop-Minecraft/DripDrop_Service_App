package com.gitalive.myapplication.ui.bing

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class BingViewModel(application: Application) : AndroidViewModel(application) {
    private val url = "https://cn.bing.com"
    private var urlBing = MutableLiveData(url)
    private val pageNotFound = "https://git-a-live.github.io/notfound.html"
    private val shp = application.getSharedPreferences("BING", Context.MODE_PRIVATE)
    private val editor = shp.edit()

    fun getHomepage(): String {
        return url
    }

    fun getBing(): MutableLiveData<String> {
        val s = shp.getString("BING", url)
        if (s != null && (s.startsWith("http") || s.startsWith("https"))) {
            urlBing.value = s
        } else {
            urlBing.value = url
        }
        return urlBing
    }

    fun setBing(link: String?) {
        if (link != null && (link.startsWith("http") || link.startsWith("https"))) {
            urlBing.value = link
            editor.putString("BING", link)
            editor.apply()
        }
    }
}
