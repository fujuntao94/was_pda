package com.sobuy.pda.component.splash

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import com.permissionx.guolindev.PermissionX
import com.sobuy.pda.R
import com.sobuy.pda.activity.BaseLogicActivity
import com.sobuy.pda.utils.DefaultPreferenceUtil

class SplashActivity : BaseLogicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
    }

    override fun initViews() {
        super.initViews()
    }

    private fun requestPermission() {
        PermissionX.init(this).permissions(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.BLUETOOTH_SCAN
        ).request { allGranted, grantedList, deniedList ->
            if (allGranted) {
                prepareNext()
            } else {
                finish()
            }
        }
    }

    private fun prepareNext() {
        Log.d(TAG, "prepareNext: ")
    }

    override fun initDatum() {
        super.initDatum()
        if (DefaultPreferenceUtil.getInstance(this).isAcceptTermsServiceAgreement) {
            requestPermission()
        } else {
            showTermsServiceAgreementDialog()
        }
    }

    private fun showTermsServiceAgreementDialog() {
        TermServiceDialogFragment.show(
            supportFragmentManager
        ) {
            Log.d(TAG, "primary Click")
            requestPermission()
            DefaultPreferenceUtil.getInstance(this).setAcceptTermsServiceAgreement();
        };
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}