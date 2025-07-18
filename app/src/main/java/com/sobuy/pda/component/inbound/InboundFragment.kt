package com.sobuy.pda.component.inbound

import android.os.Bundle
import com.sobuy.pda.component.unloading.list.UnloadingListActivity
import com.sobuy.pda.databinding.FragmentInboundBinding
import com.sobuy.pda.fragment.BaseViewModelFragment

class InboundFragment :
    BaseViewModelFragment<FragmentInboundBinding>(FragmentInboundBinding::inflate) {

    override fun initListeners() {
        super.initListeners()
        binding.unloading.setOnClickListener {
            startActivity(UnloadingListActivity::class.java)
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