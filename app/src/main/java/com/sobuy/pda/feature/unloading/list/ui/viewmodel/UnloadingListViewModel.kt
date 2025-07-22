package com.sobuy.pda.feature.unloading.list.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sobuy.pda.feature.unloading.list.data.model.UnloadingListTopItem

class UnloadingListViewModel : ViewModel() { // 使用 MutableLiveData 存储列表数据
    private val _itemList = MutableLiveData<List<UnloadingListTopItem>>()
    val itemList: LiveData<List<UnloadingListTopItem>> get() = _itemList

    init {
        // 初始化数据
        loadItems()
    }

    // 加载数据
    private fun loadItems() {
        val items = mutableListOf<UnloadingListTopItem>()
        for (i in 1..10) {
            items.add(UnloadingListTopItem(text = "Item $i", id = i.toString()))
        }
        _itemList.value = items
    }

    // 处理项目选中
    fun selectItem(position: Int ) {
        val currentItems = _itemList.value ?: emptyList()

        // 创建新的列表实例
        val updatedItems = currentItems.mapIndexed { index, item ->
            // 选中指定位置，其他位置取消选中
            if (index == position) {
                item.copy(isSelected = true)
            } else {
                item.copy(isSelected = false)
            }
        }
        Log.d("updatedItems", "selectItem: $updatedItems")
        // 更新 LiveData，触发观察者
        _itemList.value = updatedItems
    }

}