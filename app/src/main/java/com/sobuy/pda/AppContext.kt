package com.sobuy.pda

import android.app.Application
import android.util.Log
import com.polidea.rxandroidble3.RxBleClient
import com.tencent.mmkv.MMKV

class AppContext: Application() {

    override fun onCreate() {
        super.onCreate()
        rxBleClient = RxBleClient.create(this)
        instance = this
        initMMKV()
    }

    private fun initMMKV() {
        val rootDir = MMKV.initialize(this)
        Log.d(TAG, "initMMKV: $rootDir")
    }

    companion object {
        const val TAG = "AppContext"

        lateinit var instance: AppContext

        lateinit var rxBleClient: RxBleClient

    }
}