package com.gitalive.isat.ui.wiki

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class WikiViewModel(application: Application) : AndroidViewModel(application) {
    val url = "https://minecraft-zh.gamepedia.com/Minecraft_Wiki"

    fun getWiki(): MutableLiveData<String> {
        return MutableLiveData(url)
    }
}