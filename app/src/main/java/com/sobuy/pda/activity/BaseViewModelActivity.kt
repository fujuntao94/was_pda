package com.sobuy.pda.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.sobuy.pda.utils.ReflectUtil

open class BaseViewModelActivity<VB : ViewBinding> : BaseLogicActivity() {
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ReflectUtil.newViewBinding(layoutInflater, javaClass)

        setContentView(binding.root)
    }
}