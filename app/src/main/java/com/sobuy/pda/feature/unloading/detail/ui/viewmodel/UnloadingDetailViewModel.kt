package com.sobuy.pda.feature.unloading.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sobuy.pda.feature.unloading.detail.data.model.TableData

class UnloadingDetailViewModel : ViewModel() {
    private val _tableData = MutableLiveData<List<TableData>>()
    val tableData: LiveData<List<TableData>> = _tableData

    init {
        loadTableData()
    }

    private fun loadTableData() {
        // 这里可以从网络、数据库或其他数据源获取数据
        val data = mutableListOf<TableData>()

        // 添加示例数据
        for (i in 1..50) {
            data.add(
                TableData(
                    id = "FNS0081",
                    name = "37",
                    age = (20 + i % 30).toString(),
                    occupation = "7.22/189"
                )
            )
        }

        _tableData.value = data
    }
}