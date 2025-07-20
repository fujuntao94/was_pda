package com.sobuy.pda.feature.inbound.ui.fragment

import android.os.Bundle
import com.sobuy.pda.feature.clearanceorder.detail.ui.activity.ClearanceOrderDetailActivity
import com.sobuy.pda.feature.unloading.list.ui.activity.UnloadingListActivity
import com.sobuy.pda.core.base.fragment.BaseViewModelFragment
import com.sobuy.pda.databinding.FragmentInboundBinding

class InboundFragment :
    BaseViewModelFragment<FragmentInboundBinding>(FragmentInboundBinding::inflate) {

    override fun initListeners() {
        super.initListeners()
        binding.unloading.setOnClickListener {
            startActivity(UnloadingListActivity::class.java)
        }

        binding.clearanceOrder.setOnClickListener {
            startActivity(ClearanceOrderDetailActivity::class.java)
        }
    }

    override fun initViews() {
        super.initViews()
    }
    companion object {
        fun newInstance(): InboundFragment {
            val args = Bundle()
            val fragment = InboundFragment()

            fragment.arguments = args;

            return fragment;
        }
    }
}