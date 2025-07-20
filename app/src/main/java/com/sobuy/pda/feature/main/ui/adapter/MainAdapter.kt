package com.sobuy.pda.feature.main.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sobuy.pda.feature.inbound.ui.fragment.InboundFragment
import com.sobuy.pda.feature.inventory.ui.fragment.InventoryFragment
import com.sobuy.pda.feature.outbound.ui.fragment.OutboundFragment
import com.sobuy.pda.feature.my.ui.fragment.MyFragment

class MainAdapter(fragmentActivity: FragmentActivity, private val count: Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InboundFragment.Companion.newInstance()
            1 -> OutboundFragment.Companion.newInstance()
            2 -> InventoryFragment.Companion.newInstance()
            3 -> MyFragment.Companion.newInstance()
            else -> InboundFragment.Companion.newInstance()
        }
    }
}