package com.gitalive.myapplication.ui.wiki

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class WikiViewModel(application: Application) : AndroidViewModel(application) {
    val url = "https://minecraft-zh.gamepedia.com/Minecraft_Wiki"
    private var urlWiki = MutableLiveData(url)
    private val shp = application.getSharedPreferences("WIKI", Context.MODE_PRIVATE)
    private val editor = shp.edit()

    fun getWiki(): MutableLiveData<String> {
        urlWiki.value = shp.getString("WIKI", url)
        return urlWiki
    }

    fun setWiki(link: String?) {
        urlWiki.value = link
        editor.putString("WIKI", link)
        editor.apply()
    }
}