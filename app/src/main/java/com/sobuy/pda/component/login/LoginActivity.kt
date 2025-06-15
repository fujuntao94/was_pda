package com.sobuy.pda.component.login

import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivityLoginBinding

class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}