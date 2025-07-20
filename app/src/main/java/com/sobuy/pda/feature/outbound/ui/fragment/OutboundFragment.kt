package com.sobuy.pda.feature.outbound.ui.fragment

import android.os.Bundle
import com.sobuy.pda.core.base.fragment.BaseViewModelFragment
import com.sobuy.pda.databinding.FragmentOutboundBinding

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