package com.sobuy.pda.component.print

import android.util.Log
import com.sobuy.pda.activity.BaseViewModelActivity
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