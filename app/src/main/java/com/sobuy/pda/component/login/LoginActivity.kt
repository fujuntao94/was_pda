package com.sobuy.pda.component.login

import android.util.Log
import com.sobuy.pda.BuildConfig
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.config.Config
import com.sobuy.pda.databinding.ActivityLoginBinding
import com.sobuy.pda.utils.PreferenceUtil

class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
        Log.d(TAG, "initDatum: ${Config.API_URL}")
    }

    override fun initListeners() {
        super.initListeners()

//        binding.jumpToEnd.setOnClickListener {
//            setShowGuide()
//        }
    }

    fun setShowGuide() {
        PreferenceUtil.setShowGuide(false)
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}