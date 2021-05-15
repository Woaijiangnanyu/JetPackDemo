package com.guojl.jetpack.utils

import androidx.lifecycle.*
import java.util.concurrent.ConcurrentHashMap

object HiDataBus {

    private val eventMap = ConcurrentHashMap<String, StickyLiveData<*>>()
    fun <T> with(eventName: String): StickyLiveData<T> {
        // 基于事件名称 订阅、分发消息
        // 由于一个 livedata 只能发送一种数据类型
        // 所以不同的event事件，需要使用不同的livedata实例去分发
        var liveData = eventMap[eventName]
        if (liveData == null) {
            liveData = StickyLiveData<T>(eventName)
            eventMap[eventName] = liveData
        }
        return liveData as StickyLiveData<T>
    }

    class StickyLiveData<T>(private val eventName: String) : LiveData<T>() {
        internal var mStickyData: T? = null
        internal var mVersion = 0
        private val sticky: Boolean = false
        fun setStickyData(stickyData: T) {
            this.mStickyData = stickyData
            setValue(stickyData)
            //只在主线发送数据
        }

        fun postStickyData(stickyData: T) {
            this.mStickyData = stickyData
            postValue(stickyData)
            // 不受线程的限制
        }

        override fun setValue(value: T) {
            mVersion++
            super.setValue(value)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            observerSticky(owner, false, observer) //这里是为了屏蔽黏性事件
        }

        private fun observerSticky(
            owner: LifecycleOwner,
            sticky: Boolean,
            observer: Observer<in T>
        ) {
            //允许指定观察者 是否需要关心黏性事件
            // sticky = true ,如果之前已经存在发送的数据了，那这个observer会收到之前的黏性事件消息
            owner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    eventMap.remove(eventName)
                }
            })
            super.observe(owner, StickyObserver(this, sticky, observer))
        }

    }

    class StickyObserver<T>(
        val stickyLiveData: StickyLiveData<T>,
        val sticky: Boolean,
        val observer: Observer<in T>
    ) : Observer<T> {
        // lastVersion 和livedata的version 对齐原因，控制黏性事件分发
        // sticky 不等于true ，只能接收到注册之后的消息发送，如果要收到黏性事件，则sticky需要传true
        private var lastVersion = stickyLiveData.mVersion
        override fun onChanged(t: T) {
            if (lastVersion >= stickyLiveData.mVersion) {
                // stickyLiveData 没有更新数据
                if (sticky && stickyLiveData.mStickyData != null) {
                    observer.onChanged(stickyLiveData.mStickyData)
                }
                return
            }
            lastVersion = stickyLiveData.mVersion
            observer.onChanged(t)
        }

    }
}