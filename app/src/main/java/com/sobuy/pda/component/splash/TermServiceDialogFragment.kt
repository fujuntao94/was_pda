package com.sobuy.pda.component.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.sobuy.pda.R
import com.sobuy.pda.fragment.BaseDialogFragment
import com.sobuy.pda.utils.ScreenUtil

 class TermServiceDialogFragment : BaseDialogFragment() {
     override fun initViews() {
         super.initViews()
         isCancelable = true
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
         fun show(fragmentManager: FragmentManager) {
             val dialogFragment = TermServiceDialogFragment()
             dialogFragment.show(fragmentManager, "TermServiceDialogFragment")
         }
     }
}