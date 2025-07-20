package com.sobuy.pda.feature.clearanceorder.detail.ui.activity

import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivityClearanceOrderDetailBinding
import com.sobuy.pda.feature.clearanceorder.list.ui.activity.ClearanceOrderListActivity

class ClearanceOrderDetailActivity :
    BaseViewModelActivity<ActivityClearanceOrderDetailBinding>(ActivityClearanceOrderDetailBinding::inflate) {

    override fun initListeners() {
        super.initListeners()

        binding.next.setOnClickListener {
            startActivity(ClearanceOrderListActivity::class.java)
        }
    }
}