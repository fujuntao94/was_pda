package com.sobuy.pda.feature.outbound.ui.fragment

import android.os.Bundle
import com.sobuy.pda.core.base.fragment.BaseViewModelFragment
import com.sobuy.pda.databinding.FragmentOutboundBinding
import com.sobuy.pda.feature.sendingorder.list.ui.activity.SendingOrderListActivity

class OutboundFragment :
    BaseViewModelFragment<FragmentOutboundBinding>(FragmentOutboundBinding::inflate) {
    override fun initListeners() {
        super.initListeners()

        binding.SendOrder.setOnClickListener {
            startActivity(SendingOrderListActivity::class.java)
        }
    }

    companion object {
        fun newInstance(): OutboundFragment {
            val args = Bundle()
            val fragment = OutboundFragment()

            fragment.arguments = args;

            return fragment;
        }
    }
}