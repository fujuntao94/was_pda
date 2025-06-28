package com.sobuy.pda.component.my

import android.util.Log
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.sobuy.pda.R
import com.sobuy.pda.databinding.FragmentDialogExitBinding
import com.sobuy.pda.fragment.BaseViewModelDialogFragment
import com.sobuy.pda.process.SuperProcessUtil
import com.sobuy.pda.utils.ScreenUtil

class ExitDialogFragment :
    BaseViewModelDialogFragment<FragmentDialogExitBinding>(FragmentDialogExitBinding::inflate) {
    private lateinit var onAgreementClickListener: OnClickListener

    override fun initViews() {
        super.initViews()
        isCancelable = true
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.dialog_rounded_bg)
    }

    override fun initDatum() {}

    override fun initListeners() {
        super.initListeners()
        binding.yes.setOnClickListener {
            onAgreementClickListener.onClick(it)
            dismiss()
            SuperProcessUtil.killApp()
        }
        binding.cancel.setOnClickListener {
//            dismiss()
//            SuperProcessUtil.killApp()
        }
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes;
        Log.d(TAG, "onResume: $params")
        params.width = ((ScreenUtil.getScreenWidth(requireContext()) * 0.7).toInt())
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams;
    }

    companion object {
        fun show(fragmentManager: FragmentManager, onAgreementClickListener: OnClickListener) {
            val dialogFragment = ExitDialogFragment()
            dialogFragment.onAgreementClickListener = onAgreementClickListener;
            dialogFragment.show(fragmentManager, "TermServiceDialogFragment")
        }

        const val TAG = "ExitDialogFragment"

    }

}