package com.sobuy.pda.component.login


import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.api.DefaultNetworkService
import com.sobuy.pda.component.main.MainActivity
import com.sobuy.pda.config.Config
import com.sobuy.pda.databinding.ActivityLoginBinding
import com.sobuy.pda.utils.PreferenceUtil
import com.sobuy.pda.utils.BleViewModel
import kotlinx.coroutines.launch

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
        binding.submitButton.setOnClickListener {
            startActivityAfterFinishThis(MainActivity::class.java)
        }
        binding.savePassword.setOnClickListener {
            binding.circleView.isSelected = !binding.circleView.isSelected
        }

        binding.lostPassword.setOnClickListener {
            LostPasswordFragment.show(supportFragmentManager)
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
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}

