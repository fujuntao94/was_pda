package com.sobuy.pda.component.sync

import androidx.core.content.ContextCompat
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivitySyncBinding
import com.sobuy.pda.R

class SyncInventoryActivity :
    BaseViewModelActivity<ActivitySyncBinding>(ActivitySyncBinding::inflate) {


    override fun initListeners() {
        super.initListeners()

        binding.la1.setOnClickListener {
            binding.la1.setBackgroundColor(
                ContextCompat.getColor(this, R.color.primary)
            )
        }
    }
}