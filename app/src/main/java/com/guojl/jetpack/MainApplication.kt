package com.guojl.jetpack

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import com.guojl.jetpack.owner.AppLifeOwner

class MainApplication : Application(){
    private val count = 0
    var appLifecycleOwner:AppLifeOwner?= null
    override fun onCreate() {
        super.onCreate()
        appLifecycleOwner = AppLifeOwner()
        appLifecycleOwner!!.initApp(this)
    }
}