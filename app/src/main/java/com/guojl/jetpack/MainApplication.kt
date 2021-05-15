package com.guojl.jetpack

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.guojl.jetpack.owner.AppLifeOwner
import com.guojl.jetpack.utils.HiDataBus

class MainApplication : Application(){
    private val count = 0
    override fun onCreate() {
        super.onCreate()
//        appLifecycleOwner = AppLifeOwner()
//        appLifecycleOwner!!.initApp(this)
    }

}