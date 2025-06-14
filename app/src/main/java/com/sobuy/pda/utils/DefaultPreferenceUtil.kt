package com.sobuy.pda.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class DefaultPreferenceUtil(context: Context) {
    private var context: Context
    private var preference: SharedPreferences

    init {
        this.context = context.applicationContext
        preference = PreferenceManager.getDefaultSharedPreferences(this.context);
    }


    fun setAcceptTermsServiceAgreement() {
        putBoolean(TERMS_SERVICE, true)
    }


    val isAcceptTermsServiceAgreement: Boolean get() = preference.getBoolean(TERMS_SERVICE, false)

    private fun putBoolean(key: String, b: Boolean) {
        preference.edit { putBoolean(key, b) }
    }

    companion object {
        private const val TERMS_SERVICE = "TERMS_SERVICE";
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