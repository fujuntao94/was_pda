package com.sobuy.pda.feature.unloading.list.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sobuy.pda.feature.unloading.list.data.model.UnloadingListChildrenItem
import com.sobuy.pda.feature.unloading.list.data.model.UnloadingListTopItem

class UnloadingListViewModel : ViewModel() {
    private val _itemList = MutableLiveData<List<UnloadingListTopItem>>()
    val itemList: LiveData<List<UnloadingListTopItem>> get() = _itemList

    private val _selectedChildren = MutableLiveData<List<UnloadingListChildrenItem>>()
    val selectedChildren: LiveData<List<UnloadingListChildrenItem>> get() = _selectedChildren

    init {
        loadItems()
    }

    private fun loadItems() {
        val items = mutableListOf<UnloadingListTopItem>()
        for (i in 1..10) {
            items.add(
                UnloadingListTopItem(
                    text = "Item $i", id = i.toString(), children = listOf(
                        UnloadingListChildrenItem(id = "${i}_child1", text = "${i}_child1"),
                        UnloadingListChildrenItem(id = "${i}_child2", text = "${i}_child2")
                    )
                )
            )
        }
        _itemList.value = items
    }

    // Selected for project processing
    fun selectItem(position: Int) {
        val currentItems = _itemList.value ?: emptyList()

        // Check the validity of the location
        if (position < 0 || position >= currentItems.size) {
            return // Invalid location, returning directly
        }

        // Switch the selected state of the current position (selected→cancelled, unselected→selected)
        val updatedItems = currentItems.mapIndexed { index, item ->
            if (index == position) {
                item.copy(isSelected = !item.isSelected) // Switch selected state
            } else {
                item // Other items remain unchanged
            }
        }

        // Update LiveData and trigger observers
        _itemList.value = updatedItems

        collectSelectedChildren(updatedItems)
    }

    // Collect the child elements of the selected item
    private fun collectSelectedChildren(items: List<UnloadingListTopItem>) {
        val children = items
            .filter { it.isSelected } // Filter the selected parent items
            .flatMap { it.children.toList() } // Expand the sub-element array into a list

        _selectedChildren.value = children
    }

    // Clear all selected states
    fun clearSelection() {
        val updatedItems = _itemList.value?.map { it.copy(isSelected = false) } ?: emptyList()
        _itemList.value = updatedItems
        _selectedChildren.value = emptyList()
    }
}