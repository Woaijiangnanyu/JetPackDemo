package com.guojl.jetpack.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    // Create a LiveData with a String
    private var mCurrentName: MutableLiveData<String>? = null

    // Create a LiveData with a String list
    private var mNameListData: MutableLiveData<List<String>>? = null

    fun getCurrentName(): MutableLiveData<String>? {
        if (mCurrentName == null) {
            mCurrentName = MutableLiveData()
        }
        return mCurrentName
    }

    fun getNameList(): MutableLiveData<List<String>>? {
        if (mNameListData == null) {
            mNameListData = MutableLiveData()
        }
        return mNameListData
    }
}