package com.sobuy.pda.component.splash

import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.sobuy.pda.databinding.FragmentDialogTermServiceBinding
import com.sobuy.pda.fragment.BaseViewModelDialogFragment
import com.sobuy.pda.process.SuperProcessUtil
import com.sobuy.pda.utils.ScreenUtil

class TermServiceDialogFragment : BaseViewModelDialogFragment<FragmentDialogTermServiceBinding>() {

    private lateinit var onAgreementClickListener: OnClickListener

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
        fun show(fragmentManager: FragmentManager, onAgreementClickListener: OnClickListener) {
            val dialogFragment = TermServiceDialogFragment()
            dialogFragment.onAgreementClickListener = onAgreementClickListener;
            dialogFragment.show(fragmentManager, "TermServiceDialogFragment")
        }
    }
}