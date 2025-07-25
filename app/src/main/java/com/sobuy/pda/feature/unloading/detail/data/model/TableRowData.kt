package com.sobuy.pda.feature.unloading.detail.data.model

data class TableRowData(
    val skuId: String,
    val loadedQty: Int,
    val qtyToUnload: Int,
    val cbmWei: String
)