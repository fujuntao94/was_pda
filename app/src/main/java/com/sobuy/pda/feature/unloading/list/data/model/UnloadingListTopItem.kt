package com.sobuy.pda.feature.unloading.list.data.model

data class UnloadingListTopItem(
    val text: String,
    val id: String,
    var isSelected: Boolean = false
)