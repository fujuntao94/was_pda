package com.sobuy.pda.utils

import com.tencent.mmkv.MMKV

object PreferenceUtil {
    val p: MMKV by lazy {
        MMKV.defaultMMKV()
    }

    fun isShowGuide(): Boolean {
        return getBoolean(SHOW_GUIDE, true)
    }

    fun setShowGuide(boolean: Boolean) {
        putBoolean(SHOW_GUIDE, boolean)
    }

    //region 辅助方法
    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return p.getBoolean(key, defaultValue)
    }

    private fun putBoolean(key: String, defaultValue: Boolean) {
        p.edit().putBoolean(key, defaultValue).apply()
    }
    //endregion

    private const val SHOW_GUIDE = "SHOW_GUIDE"
}