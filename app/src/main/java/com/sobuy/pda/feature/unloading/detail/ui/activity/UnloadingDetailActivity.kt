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
    private var columnWidths: List<Int>? = null

    private var contentData: List<List<String>> = emptyList() // 新增：内容数据
    private var contentAdapter: RecyclerView.Adapter<*>? = null // 内容区适配器

    override fun initViews() {
        super.initViews()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // 观察表格数据
        viewModel.tableData.observe(this) { tableData ->
            if (tableData.isNotEmpty()) {
                contentData = tableData.drop(1) // 先保存内容数据
                setupHeaderRecyclerView(tableData[0]) // 只初始化表头，内容区在表头测量后初始化
            }
        }
    }

    // 设置表头RecyclerView（固定顶部，只水平滚动）
    private fun setupHeaderRecyclerView(headerData: List<String>) {
        val headerAdapter = TableAdapter(headerData, isHeader = true)
        binding.headerRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@UnloadingDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = headerAdapter

            // 测量表头列宽，用于内容区对齐
            doOnPreDraw {
                columnWidths = mutableListOf<Int>().apply {
                    for (i in 0 until headerData.size) {
                        val view = (layoutManager as LinearLayoutManager).findViewByPosition(i)
                        view?.let { add(it.width) }
                    }
                }

                // 表头测量完成后，初始化或刷新内容区
                if (contentAdapter == null) {
                    setupContentRecyclerView(contentData)
                } else {
                    contentAdapter?.notifyDataSetChanged()
                }
            }
        }
    }

    // 设置内容区RecyclerView（固定高度，可垂直+水平滚动）
    private fun setupContentRecyclerView(contentData: List<List<String>>) {
         contentAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_table_row, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val rowData = contentData[position]
                val rowRecyclerView = holder.itemView.findViewById<RecyclerView>(R.id.rowRecyclerView)

                // 添加空值检查（虽然布局正确时不会为null，但更安全）
                rowRecyclerView?.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = TableAdapter(rowData, columnWidths = columnWidths)
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