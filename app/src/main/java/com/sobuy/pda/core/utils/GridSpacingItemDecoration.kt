package com.sobuy.pda.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,           // 列数
    private val horizontalSpacing: Int,   // 水平间距（左右）
    private val verticalSpacing: Int,     // 垂直间距（上下）
    private val includeEdge: Boolean      // 是否包含边缘间距
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // 获取当前项的位置
        val column = position % spanCount                    // 计算当前项所在列

        if (includeEdge) {
            // 包含边缘间距的情况
            outRect.left = horizontalSpacing - column * horizontalSpacing / spanCount
            outRect.right = (column + 1) * horizontalSpacing / spanCount

            if (position < spanCount) { // 第一行
                outRect.top = verticalSpacing
            }
            outRect.bottom = verticalSpacing // 每行底部都有间距
        } else {
            // 不包含边缘间距的情况
            outRect.left = column * horizontalSpacing / spanCount
            outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount

            if (position >= spanCount) { // 非第一行
                outRect.top = verticalSpacing
            }
        }
    }
}