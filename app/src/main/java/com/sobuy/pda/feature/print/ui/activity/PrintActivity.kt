package com.sobuy.pda.feature.print.ui.activity

import android.util.Log
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivityPrintBinding

class PrintActivity: BaseViewModelActivity<ActivityPrintBinding>(ActivityPrintBinding::inflate) {
    override fun initListeners() {
        super.initListeners()

        binding.navigation.setBackOnClickListener {
            Log.d(TAG, "initListeners: 自定义")
            finish()
        }
    }

    companion object {
        const val TAG = "PrintActivity"
    }
}