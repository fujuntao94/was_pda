package com.sobuy.pda.fragment

import android.content.Intent
import android.view.View
import androidx.annotation.IdRes

abstract class BaseCommonFragment: BaseFragment() {
    fun <T: View?> findViewById(@IdRes id: Int): T {
        return requireView().findViewById(id)
    }

    fun startActivity(clazz: Class<*>?) {
        val intent = Intent(requireContext(), clazz)

        startActivity(intent)
    }

}