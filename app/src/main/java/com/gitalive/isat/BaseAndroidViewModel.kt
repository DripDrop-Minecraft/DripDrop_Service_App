package com.gitalive.isat

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application
    private var mUrl = MutableLiveData<String>()
    private var mName = ""

    private lateinit var shp: SharedPreferences
    fun setSPImpl(name: Int): BaseAndroidViewModel {
        mName = app.applicationContext.resources.getString(name)
        shp = app.getSharedPreferences(mName, Context.MODE_PRIVATE)
        return this
    }

    private fun editor(): SharedPreferences.Editor {
        return shp.edit()
    }

    fun setUrl(url: String): BaseAndroidViewModel {
        mUrl = MutableLiveData(url)
        editor().putString("url", url)
        editor().apply()
        return this
    }

    fun getUrl(): String? {
        return shp.getString("url", mUrl.value)
    }
}