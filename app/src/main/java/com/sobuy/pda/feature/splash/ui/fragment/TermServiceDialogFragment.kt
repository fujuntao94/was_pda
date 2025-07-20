package com.sobuy.pda.feature.splash.ui.fragment

import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.sobuy.pda.core.base.fragment.BaseViewModelDialogFragment
import com.sobuy.pda.core.utils.SuperProcessUtil
import com.sobuy.pda.databinding.FragmentDialogTermServiceBinding
import com.sobuy.pda.core.utils.ScreenUtil

class TermServiceDialogFragment :
    BaseViewModelDialogFragment<FragmentDialogTermServiceBinding>(FragmentDialogTermServiceBinding::inflate) {

    private lateinit var onAgreementClickListener: View.OnClickListener

    override fun initViews() {
        super.initViews()
        isCancelable = true
    }

    override fun initDatum() {}

    override fun initListeners() {
        super.initListeners()
        binding.primary.setOnClickListener {
            dismiss()
            onAgreementClickListener.onClick(it)
        }

        binding.disagree.setOnClickListener {
            dismiss()
            SuperProcessUtil.killApp()
        }
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes;
        params.width = ((ScreenUtil.getScreenWidth(requireContext()) * 0.9).toInt())
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams;
    }

    companion object {
        fun show(fragmentManager: FragmentManager, onAgreementClickListener: View.OnClickListener) {
            val dialogFragment = TermServiceDialogFragment()
            dialogFragment.onAgreementClickListener = onAgreementClickListener;
            dialogFragment.show(fragmentManager, "TermServiceDialogFragment")
        }
    }
}