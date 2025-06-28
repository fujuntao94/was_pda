package com.sobuy.pda.component.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sobuy.pda.component.inbound.InboundFragment
import com.sobuy.pda.component.inventory.InventoryFragment
import com.sobuy.pda.component.my.MyFragment
import com.sobuy.pda.component.outbound.OutboundFragment

class MainAdapter(fragmentActivity: FragmentActivity, private val count: Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InboundFragment.newInstance()
            1 -> OutboundFragment.newInstance()
            2 -> InventoryFragment.newInstance()
            3 -> MyFragment.newInstance()
            else -> InboundFragment.newInstance()
        }
    }
}