package com.sobuy.pda.component.my

import android.graphics.Color
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.FragmentManager
import com.sobuy.pda.databinding.FragmentDialogMyBluetoothBinding
import com.sobuy.pda.fragment.BaseViewModelDialogFragment
import com.sobuy.pda.utils.ScreenUtil

class BluetoothDialogFragment :
    BaseViewModelDialogFragment<FragmentDialogMyBluetoothBinding>(FragmentDialogMyBluetoothBinding::inflate) {
    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            setDimAmount(0f)
            setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

            setLayout(
                ((ScreenUtil.getScreenWidth(requireContext()) * 0.65).toInt()),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
    companion object {
        fun show(fragmentManager: FragmentManager) {
            val dialogFragment = BluetoothDialogFragment()
            dialogFragment.show(fragmentManager, "BluetoothDialogFragment")
        }

        const val TAG = "BluetoothDialogFragment"

    }
}