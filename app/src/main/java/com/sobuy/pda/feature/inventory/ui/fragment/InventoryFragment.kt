package com.sobuy.pda.feature.inventory.ui.fragment

import android.os.Bundle
import com.sobuy.pda.feature.print.ui.activity.PrintActivity
import com.sobuy.pda.feature.sync.ui.activity.SyncInventoryActivity
import com.sobuy.pda.core.base.fragment.BaseViewModelFragment
import com.sobuy.pda.databinding.FragmentInventoryBinding

class InventoryFragment :
    BaseViewModelFragment<FragmentInventoryBinding>(FragmentInventoryBinding::inflate) {
    override fun initListeners() {
        super.initListeners()

        binding.print.setOnClickListener {
            startActivity(PrintActivity::class.java)
        }

        binding.sync.setOnClickListener {
            startActivity(SyncInventoryActivity::class.java)
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