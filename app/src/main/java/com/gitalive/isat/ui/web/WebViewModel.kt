package com.gitalive.isat.ui.web

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class WebViewModel(application: Application) : AndroidViewModel(application) {
    val url = "http://neverlag.gitee.io/minecraft-wiki/#/"

    fun getWeb(): MutableLiveData<String> {
        return MutableLiveData(url)
    }
}
