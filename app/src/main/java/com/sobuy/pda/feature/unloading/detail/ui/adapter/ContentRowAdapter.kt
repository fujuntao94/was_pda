package com.sobuy.pda.feature.unloading.detail.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sobuy.pda.R

class ContentRowAdapter(private val rowData: List<String>) : RecyclerView.Adapter<ContentRowAdapter.RowViewHolder>() {

    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rowRecyclerView: RecyclerView = itemView.findViewById(R.id.rowRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_table_row, parent, false)
        return RowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        holder.rowRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TableAdapter(rowData)
        }
    }

    override fun getItemCount() = 1
}