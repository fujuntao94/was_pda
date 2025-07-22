package com.sobuy.pda.feature.unloading.list.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sobuy.pda.R
import com.sobuy.pda.feature.unloading.list.data.model.UnloadingListTopItem
import com.sobuy.pda.databinding.UnloadingListTopItemBinding

class UnloadingListTopItemAdapter :
    ListAdapter<UnloadingListTopItem, UnloadingListTopItemAdapter.ItemViewHolder>(DiffCallback) {
    private var onItemClickListener: ((UnloadingListTopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = UnloadingListTopItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (itemCount == 0) return

        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }

    inner class ItemViewHolder(private val binding: UnloadingListTopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnloadingListTopItem) {
            binding.textView.text = item.text

            binding.root.setCardBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    if (item.isSelected) R.color.primary else R.color.white
                )
            )
        }
    }

    fun setOnClickListener(listener: (UnloadingListTopItem) -> Unit) {
        onItemClickListener = listener
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UnloadingListTopItem>() {
        override fun areItemsTheSame(oldItem: UnloadingListTopItem, newItem: UnloadingListTopItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnloadingListTopItem, newItem: UnloadingListTopItem): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }
}