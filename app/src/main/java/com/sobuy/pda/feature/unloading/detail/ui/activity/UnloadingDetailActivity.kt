package com.sobuy.pda.feature.unloading.detail.ui.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sobuy.pda.R
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.databinding.ActivityUnloadingDetailBinding
import com.sobuy.pda.feature.unloading.detail.ui.adapter.TableAdapter
import com.sobuy.pda.feature.unloading.detail.ui.viewmodel.UnloadingDetailViewModel

class UnloadingDetailActivity :
    BaseViewModelActivity<ActivityUnloadingDetailBinding>(ActivityUnloadingDetailBinding::inflate) {
    private val viewModel: UnloadingDetailViewModel by viewModels()
    private var contentAdapter: RecyclerView.Adapter<*>? = null // 内容区适配器

    override fun initViews() {
        super.initViews()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // 观察表格数据
        viewModel.tableData.observe(this) { tableData ->
            if (tableData.isNotEmpty()) {
                setupContentRecyclerView(tableData)
            }
        }
    }

    // 设置内容区RecyclerView（固定高度，可垂直+水平滚动）
    private fun setupContentRecyclerView(contentData: List<List<String>>) {
        contentAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_table_row, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val rowData = contentData[position]
                val rowRecyclerView =
                    holder.itemView.findViewById<RecyclerView>(R.id.rowRecyclerView)

                // 添加空值检查（虽然布局正确时不会为null，但更安全）
                rowRecyclerView?.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = TableAdapter(rowData)
                } ?: throw IllegalStateException("未找到 rowRecyclerView，请检查布局文件")
            }

            override fun getItemCount() = contentData.size
        }

        binding.rowRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@UnloadingDetailActivity)
            adapter = contentAdapter

            // 监听内容区水平滚动，同步表头
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    binding.headerRecyclerView.scrollBy(dx, 0) // 同步表头水平滚动
                }
            })
        }
    }
}