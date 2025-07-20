package com.sobuy.pda.feature.my.ui.fragment

import android.os.Bundle
import android.util.Log
import com.sobuy.pda.core.base.fragment.BaseViewModelFragment
import com.sobuy.pda.databinding.FragmentMyBinding

class MyFragment : BaseViewModelFragment<FragmentMyBinding>(FragmentMyBinding::inflate) {
    override fun initListeners() {
        binding.exit.setOnClickListener {
            showExitDialog()
        }

        binding.bluetoothPrinter.setOnClickListener {
            showBluetoothDialog()
        }
    }

    private fun showExitDialog() {
        ExitDialogFragment.Companion.show(
            childFragmentManager
        ) {
            Log.d(TAG, "primary Click")
        }
    }

    private fun showBluetoothDialog() {
        BluetoothDialogFragment.Companion.show(
            childFragmentManager
        )
    }

    companion object {
        fun newInstance(): MyFragment {
            val args = Bundle()
            val fragment = MyFragment()

            fragment.arguments = args;

            return fragment;
        }

        const val TAG = "MyFragment"
    }
}