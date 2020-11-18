package com.gitalive.myapplication.ui.bbs

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class BBSViewModel(application: Application) : AndroidViewModel(application) {
    val url = "https://www.mcbbs.net/"
    private var urlBBS = MutableLiveData(url)
    private val shp = application.getSharedPreferences("BBS", MODE_PRIVATE)
    private val editor = shp.edit()

    fun getBBS(): MutableLiveData<String> {
        urlBBS.value = shp.getString("BBS", url)
        return urlBBS
    }

    fun setBBS(link: String?) {
        urlBBS.value = link
        editor.putString("BBS", link)
        editor.apply()
    }
}