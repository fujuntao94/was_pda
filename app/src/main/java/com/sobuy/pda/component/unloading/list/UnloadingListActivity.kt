package com.sobuy.pda.component.unloading.list

import android.util.Log
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivityUnloadingListBinding

class UnloadingListActivity :
    BaseViewModelActivity<ActivityUnloadingListBinding>(ActivityUnloadingListBinding::inflate) {
    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
    }

    override fun initListeners() {
        super.initListeners()
//        Log.d(TAG, "initListeners: ${viewModel.scannedDevices}")
//        binding.jumpToEnd.setOnClickListener {
////            viewModel.startScan()
//
//        }
    }
}