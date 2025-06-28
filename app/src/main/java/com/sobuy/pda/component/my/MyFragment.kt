package com.sobuy.pda.component.my

import android.os.Bundle
import com.sobuy.pda.databinding.FragmentMyBinding
import com.sobuy.pda.fragment.BaseViewModelFragment

class MyFragment : BaseViewModelFragment<FragmentMyBinding>(FragmentMyBinding::inflate) {
    companion object {
        fun newInstance(): MyFragment {
            val args = Bundle()
            val fragment = MyFragment()

            fragment.arguments = args;

            return fragment;
        }
    }
}