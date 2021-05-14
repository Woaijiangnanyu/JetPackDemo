package com.guojl.jetpack.observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainObserver:LifecycleEventObserver {
    val tag = MainObserver::class.java.name
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.i(tag,event.name)
    }
}