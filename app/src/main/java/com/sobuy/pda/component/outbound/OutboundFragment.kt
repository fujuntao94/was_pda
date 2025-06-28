package com.sobuy.pda.component.outbound

import android.os.Bundle
import com.sobuy.pda.databinding.FragmentOutboundBinding
import com.sobuy.pda.fragment.BaseViewModelFragment

class OutboundFragment : BaseViewModelFragment<FragmentOutboundBinding>(FragmentOutboundBinding::inflate) {
    companion object {
        fun newInstance(): OutboundFragment {
            val args = Bundle()
            val fragment = OutboundFragment()

            fragment.arguments = args;

            return fragment;
        }
    }
}