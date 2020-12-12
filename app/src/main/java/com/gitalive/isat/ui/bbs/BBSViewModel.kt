package com.gitalive.isat.ui.bbs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class BBSViewModel(application: Application) : AndroidViewModel(application) {
    private val url = "http://www.mcbbs.net/"

    fun getBBS(): MutableLiveData<String> {
        return MutableLiveData(url)
    }
}