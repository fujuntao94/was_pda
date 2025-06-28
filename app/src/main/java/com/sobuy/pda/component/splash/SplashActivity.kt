package com.sobuy.pda.component.splash

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import com.permissionx.guolindev.PermissionX
import com.sobuy.pda.component.main.MainActivity
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.component.login.LoginActivity
import com.sobuy.pda.databinding.ActivitySplashBinding
import com.sobuy.pda.utils.DefaultPreferenceUtil
import com.sobuy.pda.utils.PreferenceUtil
import android.bluetooth.*


class SplashActivity :
    BaseViewModelActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()


    override fun initViews() {
        super.initViews()
    }

    private fun requestPermission() {
        val permissionsToRequest = mutableListOf<String>().apply {

            // API 31+ 需要的蓝牙扫描权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                add(Manifest.permission.BLUETOOTH_SCAN)
                add(Manifest.permission.BLUETOOTH_CONNECT)
            } else {
                // API 31 以下版本使用旧的蓝牙权限
                add(Manifest.permission.BLUETOOTH_ADMIN)
                add(Manifest.permission.BLUETOOTH)
            }

            // 位置权限（API 30 及以下版本需要）
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
                add(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
        PermissionX.init(this).permissions(
            permissionsToRequest
        ).request { allGranted, grantedList, deniedList ->
            Log.d(TAG, "requestPermission: $allGranted")
            if (allGranted) {
                binding.root.postDelayed({
                    if (bluetoothAdapter == null) {
                        Log.d(TAG, "requestPermission: 蓝牙没有")
                    } else {
                        Log.d(TAG, "requestPermission: 蓝釉有")
                    }
                    prepareNext()
                }, 1000)
            } else {
                finish()
            }
        }
    }


    private fun prepareNext() {
        Log.d(TAG, "prepareNext: ")

//        if (PreferenceUtil.isShowGuide()) {
//            startActivityAfterFinishThis(LoginActivity::class.java)
//            return
//        }
        startActivityAfterFinishThis(MainActivity::class.java)
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