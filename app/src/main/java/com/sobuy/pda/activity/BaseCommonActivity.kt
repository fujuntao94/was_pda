package com.sobuy.pda.activity

import android.content.Intent

open class BaseCommonActivity: BaseActivity() {
    private fun startActivity(clazz: Class<*>?) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    fun startActivityAfterFinishThis(clazz: Class<*>?) {
        startActivity(clazz)
        finish()
    }

}