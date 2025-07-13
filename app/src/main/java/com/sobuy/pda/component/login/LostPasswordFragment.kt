package com.sobuy.pda.component.login

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.sobuy.pda.databinding.FragmentLoginLostPasswordBinding
import com.sobuy.pda.fragment.BaseViewModelDialogFragment
import com.sobuy.pda.utils.ScreenUtil
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColorInt
import com.sobuy.pda.R

class LostPasswordFragment : BaseViewModelDialogFragment<FragmentLoginLostPasswordBinding>(
    FragmentLoginLostPasswordBinding::inflate
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