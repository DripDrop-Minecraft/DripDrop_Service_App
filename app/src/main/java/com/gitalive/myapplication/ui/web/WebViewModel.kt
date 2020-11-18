package com.gitalive.myapplication.ui.web

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class WebViewModel(application: Application) : AndroidViewModel(application) {
    val url = "https://neverlag.gitee.io/patato/"
    private var urlWeb = MutableLiveData(url)
    private val shp = application.getSharedPreferences("WEB", Context.MODE_PRIVATE)
    private val editor = shp.edit()

    fun getWeb(): MutableLiveData<String> {
        urlWeb.value = shp.getString("WEB", url)
        return urlWeb
    }

    fun setWeb(link: String?) {
        urlWeb.value = link
        editor.putString("WEB", link)
        editor.apply()
    }
}
