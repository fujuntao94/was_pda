package com.sobuy.pda.component.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.sobuy.pda.R
import com.sobuy.pda.fragment.BaseCommonFragment
import com.sobuy.pda.fragment.BaseDialogFragment
import com.sobuy.pda.process.SuperProcessUtil
import com.sobuy.pda.utils.ScreenUtil

class TermServiceDialogFragment : BaseCommonFragment() {
    private lateinit var disagreeView: Button
    private lateinit var onAgreementClickListener: OnClickListener

    private lateinit var primaryView: Button

    private lateinit var contentView: TextView
    override fun initViews() {
        super.initViews()
        isCancelable = true

        contentView = findViewById(R.id.content)
        primaryView = findViewById(R.id.primary);
        disagreeView = findViewById(R.id.disagree);
    }

    override fun initDatum() {}

    override fun initListeners() {
        super.initListeners()
        primaryView.setOnClickListener {
            dismiss()
            onAgreementClickListener.onClick(it)
        }

        disagreeView.setOnClickListener {
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

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_term_service, container, false)
    }

    companion object {
        fun show(fragmentManager: FragmentManager, onAgreementClickListener: OnClickListener) {
            val dialogFragment = TermServiceDialogFragment()
            dialogFragment.onAgreementClickListener = onAgreementClickListener;
            dialogFragment.show(fragmentManager, "TermServiceDialogFragment")
        }
    }
}