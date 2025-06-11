package com.sobuy.pda.utils

import android.content.Context

class DefaultPreferenceUtil {
    fun setAcceptTermsServiceAgreement() {
        putBoolean(TERMS_SERVICE, true)
    }

    companion object {
        private var instance: DefaultPreferenceUtil? = null

        @Synchronized
        fun getInstance(context: Context): DefaultPreferenceUtil {
            if (instance == null) {
                instance = DefaultPreferenceUtil(context)
            }

            return instance!!
        }
    }
}