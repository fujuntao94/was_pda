package com.sobuy.pda.component.my

import android.os.Bundle
import android.util.Log
import com.sobuy.pda.databinding.FragmentMyBinding
import com.sobuy.pda.fragment.BaseViewModelFragment

class MyFragment : BaseViewModelFragment<FragmentMyBinding>(FragmentMyBinding::inflate) {
    override fun initListeners() {
        binding.exit.setOnClickListener {
            showExitDialog()
        }
    }

    private fun showExitDialog() {
        ExitDialogFragment.show(
            childFragmentManager
        ) {
            Log.d(TAG, "primary Click")
        }
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