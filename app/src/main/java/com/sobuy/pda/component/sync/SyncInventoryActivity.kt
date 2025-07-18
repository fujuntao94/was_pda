package com.sobuy.pda.component.sync

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.sobuy.pda.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivitySyncBinding
import com.sobuy.pda.R

class SyncInventoryActivity :
    BaseViewModelActivity<ActivitySyncBinding>(ActivitySyncBinding::inflate) {

    private var activeTab: String? = null

    override fun initListeners() {
        super.initListeners()

        binding.la1.setOnClickListener {
            handleTypeSelection(activeTab)
            binding.la1Text.setTextColor(
                ContextCompat.getColor(
                    this, R.color.white
                )
            )
            binding.la1.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.primary)
            )
            activeTab = "la1"
        }

        binding.lb1.setOnClickListener {
            handleTypeSelection(activeTab)
            binding.lb1Text.setTextColor(
                ContextCompat.getColor(
                    this, R.color.white
                )
            )
            binding.lb1.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.primary)
            )
            activeTab = "lb1"
        }

        binding.lc1.setOnClickListener {
            handleTypeSelection(activeTab)
            binding.lc1Text.setTextColor(
                ContextCompat.getColor(
                    this, R.color.white
                )
            )
            binding.lc1.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.primary)
            )
            activeTab = "lc1"
        }
    }

    private fun applyHighlightStyle(cardView: CardView, textView: TextView) {
        textView.setTextColor(ContextCompat.getColor(this, R.color.primary))
        cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.gray))
    }

    fun handleTypeSelection(type: String?) {
        when (type) {
            "la1" -> binding.la1 to binding.la1Text
            "lb1" -> binding.lb1 to binding.lb1Text
            "lc1" -> binding.lc1 to binding.lc1Text
            else -> null
        }?.let { (card, text) ->
            applyHighlightStyle(card, text)
        }
    }
}