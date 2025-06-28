package com.sobuy.pda.component.inventory

import android.os.Bundle
import com.sobuy.pda.databinding.FragmentInventoryBinding
import com.sobuy.pda.databinding.FragmentMyBinding
import com.sobuy.pda.fragment.BaseViewModelFragment

class InventoryFragment :
    BaseViewModelFragment<FragmentInventoryBinding>(FragmentInventoryBinding::inflate) {
    companion object {
        fun newInstance(): InventoryFragment {
            val args = Bundle()
            val fragment = InventoryFragment()

            fragment.arguments = args;

            return fragment;
        }
    }
}