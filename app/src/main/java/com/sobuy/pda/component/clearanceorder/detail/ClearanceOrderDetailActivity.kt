package com.sobuy.pda.component.clearanceorder.detail

import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.component.clearanceorder.list.ClearanceOrderListActivity
import com.sobuy.pda.databinding.ActivityClearanceOrderDetailBinding

class ClearanceOrderDetailActivity :
    BaseViewModelActivity<ActivityClearanceOrderDetailBinding>(ActivityClearanceOrderDetailBinding::inflate) {

    override fun initListeners() {
        super.initListeners()

        binding.next.setOnClickListener {
            startActivity(ClearanceOrderListActivity::class.java)
        }
    }
}