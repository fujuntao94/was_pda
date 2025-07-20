package com.sobuy.pda.feature.login.ui.fragment

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.sobuy.pda.databinding.FragmentDialogLoginLostPasswordBinding
import com.sobuy.pda.core.base.fragment.BaseViewModelDialogFragment
import androidx.core.graphics.drawable.toDrawable

class LostPasswordFragment : BaseViewModelDialogFragment<FragmentDialogLoginLostPasswordBinding>(
    FragmentDialogLoginLostPasswordBinding::inflate
) {


    override fun initViews() {
        super.initViews()
        isCancelable = true

        Handler(Looper.getMainLooper()).postDelayed({
            dismiss()
        }, 3000)
    }
    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()
    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            setDimAmount(0f)
            setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            val params = attributes

            params.gravity = Gravity.TOP

            params.y = 100.dpToPx()
            attributes = params
            setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    companion object {
        fun show(fragmentManager: FragmentManager) {
            val dialogFragment = LostPasswordFragment()
            dialogFragment.show(fragmentManager, "TermServiceDialogFragment")
        }
    }
}