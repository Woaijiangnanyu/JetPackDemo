package com.guojl.jetpack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.guojl.jetpack.utils.HiDataBus

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        HiDataBus.with<String>("haha").observe(this,
//            Observer<String> { t -> t?.let { Log.i("aaa", it) } })
                    HiDataBus.with<Boolean>("haha").setStickyData(false)
    }

    companion object {
        fun startSecondActivity(context: Context) {
            var intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }
    }

}