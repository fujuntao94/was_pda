package com.sobuy.pda.feature.unloading.detail.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sobuy.pda.R
import com.sobuy.pda.databinding.ItemTableRowBinding
import com.sobuy.pda.feature.unloading.detail.data.model.TableData

class TableAdapter(private val dataList: List<TableData>) :
    RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cellId: TextView = itemView.findViewById(R.id.cellId)
        val cellName: TextView = itemView.findViewById(R.id.cellName)
        val cellAge: TextView = itemView.findViewById(R.id.cellAge)
        val cellOccupation: TextView = itemView.findViewById(R.id.cellOccupation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_table_row, parent, false)
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val item = dataList[position]
        holder.cellId.text = item.id
        holder.cellName.text = item.name
        holder.cellAge.text = item.age
        holder.cellOccupation.text = item.occupation

        // 为奇数行设置不同的背景色，提高可读性
        if (position % 2 == 1) {
            holder.itemView.setBackgroundResource(R.color.gray)
        } else {
            holder.itemView.setBackgroundResource(android.R.color.white)
        }
    }
}