package com.guojl.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.guojl.jetpack.observer.MainObserver
import com.guojl.jetpack.ui.main.MainFragment
import com.guojl.jetpack.utils.HiDataBus

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        HiDataBus.with<String>("haha").postStickyData("aaaaa")
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
//        val mainObserver = MainObserver()
//        var application = application as MainApplication
//        application.appLifecycleOwner!!.lifecycleRegistry!!.addObserver(mainObserver)

    }
}