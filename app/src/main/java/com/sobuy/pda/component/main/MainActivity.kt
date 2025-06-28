package com.sobuy.pda.component.main

import com.sobuy.pda.R
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.component.login.LoginActivity
import com.sobuy.pda.databinding.ActivityMainBinding
import com.sobuy.pda.databinding.ItemTabBinding
import com.sobuy.pda.utils.Constant

class MainActivity : BaseViewModelActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun initDatum() {
        super.initDatum()

        for (i in indicatorTitles.indices) {
            ItemTabBinding.inflate(layoutInflater).apply {
                content.setText(indicatorTitles[i])
                icon.setImageResource(indicatorIcons[i])
                binding.indicator.addView(root)
            }
        }


        val action = intent.action;
        if (Constant.ACTION_LOGIN == action) {
            startActivityAfterFinishThis(LoginActivity::class.java)
        }
    }

    companion object {
        private val indicatorTitles = intArrayOf(
            R.string.discovery,
            R.string.discovery,
            R.string.video,
            R.string.category,
            R.string.me,
        )

        private val indicatorIcons = intArrayOf(
            R.drawable.selector_tab_unloading,
            R.drawable.selector_tab_printing,
            R.drawable.selector_tab_agv,
            R.drawable.selector_tab_move,
            R.drawable.selector_tab_my,
        )
    }
}