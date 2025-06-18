package com.sobuy.pda.component.login

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.sobuy.pda.BuildConfig
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.api.ApiResponse
import com.sobuy.pda.api.DefaultNetworkService
import com.sobuy.pda.api.NetworkUtils
import com.sobuy.pda.api.UserInfo
import com.sobuy.pda.config.Config
import com.sobuy.pda.databinding.ActivityLoginBinding
import com.sobuy.pda.utils.PreferenceUtil
import kotlinx.coroutines.launch

class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
        Log.d(TAG, "initDatum: ${Config.API_URL}")
    }

    private fun getCaptchaImage() {
        lifecycleScope.launch {
            val contentWrapper = DefaultNetworkService.create().captchaImageApi();
            Log.d(TAG, "getCaptchaImage: $contentWrapper")
        }
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

