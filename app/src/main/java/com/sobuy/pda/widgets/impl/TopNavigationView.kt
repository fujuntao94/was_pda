package com.sobuy.pda.widgets.impl

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sobuy.pda.R

class TopNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var textTitle: TextView
    private var leftIcon: ImageView
    private var leftClickListener: (() -> Unit)? = null

    init {
        orientation = VERTICAL

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TopNavigationView,
            0, 0
        ).apply {
            try {
                inflate(context, R.layout.custom_top_navigation_view, this@TopNavigationView)

                textTitle = findViewById(R.id.title)
                val labelText = getString(R.styleable.TopNavigationView_title)
                textTitle.text = labelText ?: "Default"

                val customBgColor = getColor(
                    R.styleable.TopNavigationView_backgroundColor,
                    ContextCompat.getColor(context, R.color.primary)
                )
                setBackgroundColor(customBgColor)

                // Left return click event
                leftIcon = findViewById(R.id.left)
                leftIcon.setOnClickListener {
                    Log.d(TAG, "setBackOnClickListener: back")
                    leftClickListener?.invoke()
                }
            } finally {
                recycle()
            }
        }
    }

    fun setBackOnClickListener(listener: () -> Unit) {
        leftClickListener = listener
    }

    companion object {
        const val TAG = "TopNavigationView"
    }
}