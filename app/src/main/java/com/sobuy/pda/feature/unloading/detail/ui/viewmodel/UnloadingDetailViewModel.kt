package com.sobuy.pda.feature.unloading.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class UnloadingDetailViewModel : ViewModel() {
    // 表格数据：表头 + 内容行
    private val _tableData = MutableLiveData<List<List<String>>>()
    val tableData: LiveData<List<List<String>>> = _tableData

    // 表格UI状态（加载中、成功、失败）
    private val _uiState = MutableLiveData<TableUiState>()
    val uiState: LiveData<TableUiState> = _uiState

    init {
        loadTableData()
    }

    // 模拟加载表格数据（实际项目中可从网络/数据库获取）
    private fun loadTableData() {
        _uiState.value = TableUiState.Loading

        // 模拟延迟加载
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            try {
                // 构建表格数据
                val headers = listOf("SKU ID", "Loaded Qty", "Qty to be unload", "CBM/Wei")
                val rows = listOf(
                    listOf("FNS0081", "37", "27", "7.22/189"),
                    listOf("FNT0126", "20", "20", "16.31/40"),
                    listOf("FNP3312", "24", "17", "22.50/33"),
                    listOf("FNC0055", "18", "18", "9.75/87"),
                    listOf("FNL0092", "32", "15", "14.30/56"),
                    listOf("FNH0105", "45", "30", "18.90/92"),
                    listOf("FNP0218", "12", "12", "6.40/41"),
                    listOf("FNS0081", "37", "27", "7.22/189"),
                    listOf("FNT0126", "20", "20", "16.31/40"),
                    listOf("FNP3312", "24", "17", "22.50/33")
                )
                _tableData.postValue(listOf(headers) + rows)
                _uiState.postValue(TableUiState.Success)
            } catch (e: Exception) {
                _uiState.postValue(TableUiState.Error("加载表格数据失败"))
            }
        }
    }

    // 刷新表格数据
    fun refreshData() {
        loadTableData()
    }

    // 表格UI状态
    sealed class TableUiState {
        object Loading : TableUiState()
        object Success : TableUiState()
        data class Error(val message: String) : TableUiState()
    }
}