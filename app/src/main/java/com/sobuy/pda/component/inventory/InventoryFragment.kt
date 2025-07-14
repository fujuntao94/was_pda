package com.sobuy.pda.component.inventory

import android.os.Bundle
import com.sobuy.pda.component.print.PrintActivity
import com.sobuy.pda.databinding.FragmentInventoryBinding
import com.sobuy.pda.fragment.BaseViewModelFragment

class InventoryFragment :
    BaseViewModelFragment<FragmentInventoryBinding>(FragmentInventoryBinding::inflate) {
    override fun initListeners() {
        super.initListeners()

        binding.print.setOnClickListener {
            startActivity(PrintActivity::class.java)
        }
    }

    companion object {
        fun newInstance(): InventoryFragment {
            val args = Bundle()
            val fragment = InventoryFragment()

            fragment.arguments = args;

            return fragment;
        }
    }
}