package com.sobuy.pda.component.login


import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.api.DefaultNetworkService
import com.sobuy.pda.config.Config
import com.sobuy.pda.databinding.ActivityLoginBinding
import com.sobuy.pda.utils.PreferenceUtil
import com.sobuy.pda.utils.BleViewModel
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private val viewModel: BleViewModel by viewModels()

    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
        Log.d(TAG, "initDatum: ${Config.API_URL}")
    }


    override fun initListeners() {
        super.initListeners()
        Log.d(TAG, "initListeners: ${viewModel.scannedDevices}")
        binding.jumpToEnd.setOnClickListener {
            testGet()
////            viewModel.startScan()
//            val client = OkHttpClient()
//
////            val url = Config.
//            val request = Request().Builder().url("").build()
        }
    }

    fun setShowGuide() {
        PreferenceUtil.setShowGuide(false)
    }

    private fun testGet() {
        lifecycleScope.launch {
           var response = DefaultNetworkService.create().captchaImageApi()
            if (response.code == 200) {
                Log.d(TAG, response.img.toString())
            }
        }
//        val client = OkHttpClient()
//        val url = "http://106.14.57.78:18086/api/captchaImage"
//        val request = Request.Builder().url(url).build()
//        client.newCall(request).enqueue(
//            object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    Log.d(TAG, "onFailure: ${e.localizedMessage}")
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    Log.d(TAG, "onResponse: ${response.body!!.string()}")
//                }
//            },
//        )
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}

