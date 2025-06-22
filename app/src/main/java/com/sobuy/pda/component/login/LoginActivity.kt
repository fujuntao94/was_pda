package com.sobuy.pda.component.login

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.polidea.rxandroidble3.scan.ScanSettings
import com.sobuy.pda.AppContext
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.api.ApiResponse
import com.sobuy.pda.api.DefaultNetworkService
import com.sobuy.pda.api.UserInfo
import com.sobuy.pda.config.Config
import com.sobuy.pda.databinding.ActivityLoginBinding
import com.sobuy.pda.utils.PreferenceUtil
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.launch

class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private var scanDisposable: Disposable? = null

    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
        startScan()
        Log.d(TAG, "initDatum: ${Config.API_URL}")
    }

    private fun getCaptchaImage() {
        lifecycleScope.launch {
            val contentWrapper = DefaultNetworkService.create().captchaImageApi();
            Log.d(TAG, "getCaptchaImage: $contentWrapper")
        }
    }
    // 开始扫描
    private fun startScan() {
        scanDisposable = AppContext.rxBleClient.scanBleDevices(
            ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build()
        )
            .subscribe(
                { scanResult ->
                    // 扫描到设备
                    val device = scanResult.bleDevice
                    Log.d("BLE", "发现设备: ${device.name ?: "未知名称"}, MAC: ${device.macAddress}")
                },
                { throwable ->
                    // 扫描出错
                    Log.e("BLE", "扫描失败: ${throwable.message}")
                }
            )
    }

    suspend fun fetchUserInfo() {
//        return NetworkUtils.safeApiCall {
            val response = RetrofitClient.apiService.getUserInfo()
            when (response) {
                is ApiResponse.Success -> {
                    Log.d(TAG, "fetchUserInfo: $response")
                    val userInfo: UserInfo = response.data
                    // 对获取到的用户信息进行处理
                }
                is ApiResponse.Error -> {
                    // 处理错误情况，例如显示错误信息
//                    Log.e("Network", "Error: ${response.message}")
                }
//                is ApiResponse.Exception -> {
                    // 处理异常情况
//                    Log.e("Network", "Exception: ${response.e.message}")
//                }

                is ApiResponse.Failure -> TODO()
            }
    }

    override fun initListeners() {
        super.initListeners()

        binding.jumpToEnd.setOnClickListener {
            lifecycleScope.launch {
                fetchUserInfo()
            }
        }
    }

    fun setShowGuide() {
        PreferenceUtil.setShowGuide(false)
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}

