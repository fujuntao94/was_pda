package com.sobuy.pda

import android.app.Application
import android.util.Log
import com.tencent.mmkv.MMKV

class AppContext: Application() {
    override fun onCreate() {
        super.onCreate()
        initMMKV()
    }

    private fun initMMKV() {
        val rootDir = MMKV.initialize(this)
        Log.d(TAG, "initMMKV: $rootDir")
    }

    companion object {
        const val TAG = "AppContext"
    }
}