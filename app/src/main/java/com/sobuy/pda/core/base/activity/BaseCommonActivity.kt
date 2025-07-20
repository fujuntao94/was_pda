package com.sobuy.pda.core.base.activity

import android.content.Intent

open class BaseCommonActivity : BaseActivity() {
    fun startActivity(clazz: Class<*>?) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    fun startActivityAfterFinishThis(clazz: Class<*>?) {
        startActivity(clazz)
        finish()
    }

}