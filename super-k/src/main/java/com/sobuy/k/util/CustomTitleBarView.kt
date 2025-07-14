package com.sobuy.k.util

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sobuy.k.R

// 自定义 View 继承自布局的根容器（LinearLayout）
class CustomTitleBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,  // 用于接收布局中传递的 app: 属性
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    // 定义回调接口
    interface OnBackClickListener {
        fun onBackClick() // 回调方法：通知宿主需要返回
    }
    // 存储回调实例
    private var backClickListener: OnBackClickListener? = null

    init {
        // 1. 加载被引入的布局（将 layout_include.xml 加载到当前自定义 View 中）
        LayoutInflater.from(context).inflate(R.layout.layout_include, this, true)

        // 2. 解析自定义属性（从 attrs 中获取布局中传递的 app: 参数）
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomIncludeView)

        // 3. 获取参数（并设置默认值，避免为空）
        val contentText = typedArray.getString(R.styleable.CustomIncludeView_contentText) ?: "默认文本"
        val textColor = typedArray.getColor(
            R.styleable.CustomIncludeView_contentTextColor,
            ContextCompat.getColor(context, android.R.color.black)  // 默认黑色
        )
        val bgColor = typedArray.getColor(
            R.styleable.CustomIncludeView_contentBgColor,
            ContextCompat.getColor(context, android.R.color.white)  // 默认白色
        )

        // 4. 回收 typedArray（必须调用，避免内存泄漏）
        typedArray.recycle()

        // 5. 将参数应用到布局内的控件
        val textView = findViewById<TextView>(R.id.contentTv)
        textView.text = contentText
        textView.setTextColor(textColor)
        this.setBackgroundColor(bgColor)  // 设置根布局背景色

        val backView = findViewById<ImageView>(R.id.back)
        backView.setOnClickListener {
            Log.d(TAG, ": ")
            // 强转上下文为Activity（需确保context是Activity）
            val activity = context as? AppCompatActivity
            activity?.finish()
        }
    }
    companion object {
        const val TAG = "CustomTitleBarView"
    }
}