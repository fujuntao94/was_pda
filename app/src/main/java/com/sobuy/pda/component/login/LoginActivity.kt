package com.sobuy.pda.component.login

import android.util.Log
import androidx.activity.viewModels
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.api.DefaultNetworkService
import com.sobuy.pda.config.Config
import com.sobuy.pda.databinding.ActivityLoginBinding
import com.sobuy.pda.utils.PreferenceUtil
import com.sobuy.pda.utils.BleViewModel


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
//            viewModel.startScan()

        }
    }

    fun setShowGuide() {
        PreferenceUtil.setShowGuide(false)
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}

