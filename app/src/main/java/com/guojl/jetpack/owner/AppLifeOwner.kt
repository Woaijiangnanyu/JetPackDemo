package com.guojl.jetpack.owner

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.guojl.jetpack.MainApplication

open class AppLifeOwner: LifecycleOwner {
    var count: Int = 0
    var lifecycleRegistry:LifecycleRegistry?= null
    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry!!
    }

    fun initApp(application: Application) {
        lifecycleRegistry = LifecycleRegistry(this)
        application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }


            override fun onActivityStarted(activity: Activity) {
                if (count == 0) {
                    lifecycleRegistry!!.handleLifecycleEvent(Lifecycle.Event.ON_START)
                }
                count++
            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {
//                lifecycleRegistry!!.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            }

            override fun onActivityStopped(activity: Activity) {
                count--
                if (count == 0) {
                    lifecycleRegistry!!.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }

        })
    }
}