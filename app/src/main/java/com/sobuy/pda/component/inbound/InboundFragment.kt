package com.sobuy.pda.component.inbound

import android.os.Bundle
import com.sobuy.pda.databinding.FragmentInboundBinding
import com.sobuy.pda.fragment.BaseViewModelFragment

class InboundFragment :
    BaseViewModelFragment<FragmentInboundBinding>(FragmentInboundBinding::inflate) {
    companion object {
        fun newInstance(): InboundFragment {
            val args = Bundle()
            val fragment = InboundFragment()

            fragment.arguments = args;

            return fragment;
        }
    }
}