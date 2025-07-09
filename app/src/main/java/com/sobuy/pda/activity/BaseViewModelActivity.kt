package com.sobuy.pda.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.sobuy.pda.R

open class BaseViewModelActivity<VB : ViewBinding>(
    private val inflate: (LayoutInflater) -> VB
) : BaseLogicActivity() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(binding.root)
        ImmersionBar.with(this)
            .statusBarColor(R.color.primary) // 设置状态栏颜色
            .fitsSystemWindows(true) // 是否把布局内容全屏显示
            .init() // 应用配置
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}