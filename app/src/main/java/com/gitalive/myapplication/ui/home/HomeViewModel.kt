package com.gitalive.myapplication.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val url = "http://txyminecraft.club:8123/"
    val update =
        """
        本次更新：
1.修复了一些潜在bug;
2.修改部分文本内容;
3.调整网页字体大小;
4.调整部分行为逻辑。
        """.trimIndent()
    val feedback: String = "有意见或建议？请在群内@Dell_G3！"
    fun getMap(): String {
        return url
    }
}