package com.sobuy.pda.feature.sendingorder.list.ui.activity

import androidx.activity.viewModels
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivitySendingOrderListBinding
import com.sobuy.pda.feature.sendingorder.list.ui.viewmodel.SendingOrderListViewModel

class SendingOrderListActivity :
    BaseViewModelActivity<ActivitySendingOrderListBinding>(ActivitySendingOrderListBinding::inflate) {
    private val viewModel: SendingOrderListViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()
    }
}