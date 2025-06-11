package com.sobuy.pda.process

import android.os.Process

object SuperProcessUtil {
    fun killApp() {
        Process.killProcess(Process.myPid())
    }
}