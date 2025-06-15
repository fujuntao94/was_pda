package com.sobuy.pda.component.splash

import android.Manifest
import android.content.Intent
import android.util.Log
import com.permissionx.guolindev.PermissionX
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.component.login.LoginActivity
import com.sobuy.pda.databinding.ActivitySplashBinding
import com.sobuy.pda.utils.DefaultPreferenceUtil

class SplashActivity :
    BaseViewModelActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun initViews() {
        super.initViews()
    }

    private fun requestPermission() {
        PermissionX.init(this).permissions(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        ).request { allGranted, grantedList, deniedList ->
            Log.d(TAG, "requestPermission: $allGranted")
            if (allGranted) {
                binding.root.postDelayed({
                    prepareNext()
                }, 1000)
            } else {
                finish()
            }
        }
    }

    private fun prepareNext() {
        Log.d(TAG, "prepareNext: ")
        startActivityAfterFinishThis(LoginActivity::class.java)
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