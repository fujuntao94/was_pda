package com.sobuy.pda.feature.unloading.detail.ui.adapter

import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sobuy.pda.R

class TableAdapter(
    private val data: List<String>,
    private val isHeader: Boolean = false,
    private val columnWidths: List<Int>? = null
) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCell: TextView = itemView.findViewById(R.id.tvCell)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_table_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCell.text = data[position]
        // 设置列宽以确保与表头对齐
        Log.d("TAG", "onBindViewHolder: $columnWidths")
        columnWidths?.takeIf { it.size > position }?.let { widths ->
            holder.itemView.layoutParams = (holder.itemView.layoutParams as ViewGroup.MarginLayoutParams).apply {
                width = widths[position]
            }
        }
        // 表头样式
        if (isHeader) {
            holder.tvCell.apply {
                setTypeface(null, Typeface.BOLD)
                setTextColor(ContextCompat.getColor(context, R.color.text))
                setBackgroundColor(ContextCompat.getColor(context, R.color.tableBack))
            }
        }

        // 特殊列样式（如Qty to be unload列显示绿色）
        if (position == 2 && !isHeader) {
            holder.tvCell.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.primary))
        }
    }

    override fun getItemCount() = data.size
}