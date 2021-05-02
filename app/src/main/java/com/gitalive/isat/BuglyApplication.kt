package com.gitalive.isat

import android.app.Application
import android.content.Context
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.crashreport.CrashReport

class BuglyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val APP_ID = ""
        //SDK初始化，APP_ID填入Bugly平台中对应产品的App ID
        Bugly.init(this, APP_ID, BuildConfig.DEBUG)
        //Crash收集上传功能初始化，APP_ID填入Bugly平台中对应产品的App ID
        CrashReport.initCrashReport(this, APP_ID, BuildConfig.DEBUG)
        //检查是否存在新版本
        Beta.checkAppUpgrade()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //安装Tinker
        Beta.installTinker(this)
    }
}