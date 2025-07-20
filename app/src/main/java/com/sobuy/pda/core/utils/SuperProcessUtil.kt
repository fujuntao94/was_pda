package com.sobuy.pda.core.utils

import android.os.Process

object SuperProcessUtil {
    fun killApp() {
        Process.killProcess(Process.myPid())
    }
}