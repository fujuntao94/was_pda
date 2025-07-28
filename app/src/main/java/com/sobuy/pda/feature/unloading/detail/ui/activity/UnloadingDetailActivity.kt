package com.sobuy.pda.feature.unloading.detail.ui.activity

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivityUnloadingDetailBinding
import com.sobuy.pda.feature.unloading.detail.ui.adapter.TableAdapter
import com.sobuy.pda.feature.unloading.detail.ui.viewmodel.UnloadingDetailViewModel

class UnloadingDetailActivity :
    BaseViewModelActivity<ActivityUnloadingDetailBinding>(ActivityUnloadingDetailBinding::inflate) {
    private val viewModel: UnloadingDetailViewModel by viewModels()

    override fun initViews() {
        super.initViews()
        // 设置RecyclerView
        binding.recyclerViewTable.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTable.setHasFixedSize(true)

        // 观察数据变化
        viewModel.tableData.observe(this) { data ->
            binding.recyclerViewTable.adapter = TableAdapter(data)
        }
    }
}