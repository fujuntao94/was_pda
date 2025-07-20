package com.sobuy.pda.feature.main.ui.activity

import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.sobuy.pda.R
import com.sobuy.pda.feature.main.ui.adapter.MainAdapter
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivityMainBinding
import com.sobuy.pda.databinding.ItemTabBinding

class MainActivity : BaseViewModelActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun initDatum() {
        super.initDatum()
        binding.apply {
            pager.offscreenPageLimit = indicatorTitles.size
            pager.adapter = MainAdapter(this@MainActivity, indicatorTitles.size)
        }

        for (i in indicatorTitles.indices) {
            ItemTabBinding.inflate(layoutInflater).apply {
                content.setText(indicatorTitles[i])
                icon.setImageResource(indicatorIcons[i])
                binding.indicator.addView(root)
            }
        }

        ViewPager2Delegate.Companion.install(binding.pager, binding.indicator, false)
    }

    companion object {
        private val indicatorTitles = intArrayOf(
            R.string.Inbound,
            R.string.Outbound,
            R.string.Inventory,
            R.string.My,
        )

        private val indicatorIcons = intArrayOf(
            R.drawable.selector_tab_inbound,
            R.drawable.selector_tab_outbound,
            R.drawable.selector_tab_inventory,
            R.drawable.selector_tab_my,
        )
    }
}