package com.sobuy.pda.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object ScreenUtil {
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val outDisplayMetrics = DisplayMetrics()

        wm.defaultDisplay.getMetrics(outDisplayMetrics);

        return outDisplayMetrics.widthPixels;
    }
}