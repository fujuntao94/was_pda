package com.sobuy.pda.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

open class BaseViewModelActivity<VB : ViewBinding>(
    private val inflate: (LayoutInflater) -> VB
) : BaseLogicActivity() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}