package com.sobuy.pda.feature.unloading.list.ui.adapter

import android.content.Context
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
            binding.apply {
                textView.text = item.text
                root.setCardBackgroundColor(
                    getItemBackgroundColor(root.context, item.isSelected)
                )
                textView.setTextColor(
                    getItemTextColor(root.context, item.isSelected)
                )
                root.animate().alpha(1f).setDuration(200).start()
            }
        }
    }

    fun setOnClickListener(listener: (UnloadingListTopItem) -> Unit) {
        onItemClickListener = listener
    }

    //  Extended function: Obtain background color based on selected state
    fun getItemBackgroundColor(context: Context, isSelected: Boolean): Int {
        return ContextCompat.getColor(
            context,
            if (isSelected) R.color.primary else R.color.gray
        )
    }

    // Extended function: Get text color based on selected state
    fun getItemTextColor(context: Context, isSelected: Boolean): Int {
        return ContextCompat.getColor(
            context,
            if (isSelected) R.color.white else R.color.primary
        )
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UnloadingListTopItem>() {
        override fun areItemsTheSame(
            oldItem: UnloadingListTopItem,
            newItem: UnloadingListTopItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UnloadingListTopItem,
            newItem: UnloadingListTopItem
        ): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }
}