package com.sobuy.pda.feature.sendingorder.submit.ui.activity

import androidx.activity.viewModels
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivitySendingOrderSubmitBinding
import com.sobuy.pda.feature.sendingorder.submit.ui.viewmodel.SendingOrderSubmitViewModel

class SendingOrderSubmitActivity :
    BaseViewModelActivity<ActivitySendingOrderSubmitBinding>(ActivitySendingOrderSubmitBinding::inflate) {
    private val viewModel: SendingOrderSubmitViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()
    }
}