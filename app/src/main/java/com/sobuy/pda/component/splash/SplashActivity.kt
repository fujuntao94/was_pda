package com.sobuy.pda.component.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sobuy.pda.R
import com.sobuy.pda.activity.BaseLogicActivity

class SplashActivity : BaseLogicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    override fun initViews() {
        super.initViews()
    }

    override fun initDatum() {
        super.initDatum()
        showTermsServiceAgreementDialog()
    }

    private fun showTermsServiceAgreementDialog() {
        TermServiceDialogFragment.show(
            supportFragmentManager
        ) { Log.d(TAG, "primary Click") };
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}