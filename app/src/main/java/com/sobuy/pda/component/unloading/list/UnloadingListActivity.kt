package com.sobuy.pda.component.unloading.list

import android.util.Log
import androidx.core.content.ContextCompat
import com.sobuy.pda.R
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivityUnloadingListBinding

class UnloadingListActivity :
    BaseViewModelActivity<ActivityUnloadingListBinding>(ActivityUnloadingListBinding::inflate) {
    private var activeTab = "first"
    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
    }

    override fun initListeners() {
        super.initListeners()
        binding.onlyInLoadingTab.setOnClickListener {
            if (activeTab == "first") {
                binding.onlyInLoadingTab.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.primary
                    )
                )
                binding.onlyInLoadingText.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.allDesksTab.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.allDesksText.setTextColor(ContextCompat.getColor(this, R.color.primary))
                activeTab = "second"
            }
        }

        binding.allDesksTab.setOnClickListener {
            if (activeTab == "second") {
                binding.onlyInLoadingTab.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.onlyInLoadingText.setTextColor(ContextCompat.getColor(this, R.color.primary))
                binding.allDesksTab.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.primary
                    )
                )
                binding.allDesksText.setTextColor(ContextCompat.getColor(this, R.color.white))
                activeTab = "first"
            }
        }
    }

    companion object {
        const val TAG = "UnloadingListActivity"
    }
}