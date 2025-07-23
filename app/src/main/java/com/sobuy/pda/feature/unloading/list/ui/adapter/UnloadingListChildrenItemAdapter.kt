package com.sobuy.pda.feature.unloading.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sobuy.pda.databinding.UnloadingListChildrenItemBinding
import com.sobuy.pda.feature.unloading.list.data.model.UnloadingListChildrenItem
import com.sobuy.pda.feature.unloading.list.data.model.UnloadingListTopItem

class UnloadingListChildrenItemAdapter :
    ListAdapter<UnloadingListChildrenItem, UnloadingListChildrenItemAdapter.ItemViewHolder>(
        DiffCallback
    ) {
    private var onItemClickListener: ((UnloadingListChildrenItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = UnloadingListChildrenItemBinding.inflate(
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

    inner class ItemViewHolder(private val binding: UnloadingListChildrenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnloadingListChildrenItem) {
            binding.apply {
//                textView.text = item.text
            }
        }
    }

    fun setOnClickListener(listener: (UnloadingListChildrenItem) -> Unit) {
        onItemClickListener = listener
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UnloadingListChildrenItem>() {
        override fun areItemsTheSame(
            oldItem: UnloadingListChildrenItem,
            newItem: UnloadingListChildrenItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UnloadingListChildrenItem,
            newItem: UnloadingListChildrenItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}