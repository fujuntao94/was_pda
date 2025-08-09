package com.sobuy.pda.widgets.impl

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.angcyo.tablayout.DslTabLayout
import com.sobuy.pda.R

class TopNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : DrawerLayout(context, attrs, defStyleAttr) {
    private var textTitle: TextView

    private var navigationBar: View
    private var leftClickListener: (() -> Unit)? = null
    // 抽屉容器（外部需传入DrawerLayout）
//    private var drawerContent: LinearLayout

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
        )

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
                navigationBar = findViewById(R.id.navigation_bar)
                navigationBar.setBackgroundColor(customBgColor)

                // Left return click event
//                leftIcon = findViewById(R.id.left)
//                leftIcon.setOnClickListener {
//                    Log.d(TAG, "setBackOnClickListener: back")
//                    leftClickListener?.invoke()
//                }
//

            } finally {
                recycle()
            }
        }
    }

    /**
     * 判断抽屉是否处于打开状态
     */
//    fun isDrawerOpen(): Boolean {
//        // 检查抽屉容器是否处于打开状态
//        return isDrawerOpen(drawerContent)
//    }
//
//    // 关闭抽屉
//    fun closeDrawer() {
//        if (isDrawerOpen(drawerContent)) {
//            closeDrawer(drawerContent)
//        }
//    }
//
//    // 打开抽屉
//    fun openDrawer() {
//        if (!isDrawerOpen(drawerContent)) {
//            openDrawer(drawerContent)
//        }
//    }
    fun setBackOnClickListener(listener: () -> Unit) {
        leftClickListener = listener
    }

    companion object {
        const val TAG = "TopNavigationView"
    }
}