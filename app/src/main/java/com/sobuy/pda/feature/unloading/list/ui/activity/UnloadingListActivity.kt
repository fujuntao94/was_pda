package com.sobuy.pda.feature.unloading.list.ui.activity

import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyf.immersionbar.ImmersionBar
import com.sobuy.pda.R
import com.sobuy.pda.feature.unloading.list.ui.viewmodel.UnloadingListViewModel
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.core.utils.GridSpacingItemDecoration
import com.sobuy.pda.databinding.ActivityUnloadingListBinding
import com.sobuy.pda.feature.unloading.list.ui.adapter.UnloadingListTopItemAdapter
import kotlin.getValue

class UnloadingListActivity :
    BaseViewModelActivity<ActivityUnloadingListBinding>(ActivityUnloadingListBinding::inflate) {
    private val viewModel: UnloadingListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private val SPAN_COUNT = 3
    private var activeTab = "first"

    override fun initListeners() {
        super.initListeners()
        binding.onlyInLoadingTab.setOnClickListener {
            if (activeTab == "first") {
                binding.onlyInLoadingTab.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.primary
                    )
                )
                binding.onlyInLoadingText.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.allDesksTab.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.allDesksText.setTextColor(ContextCompat.getColor(this, R.color.primary))
                activeTab = "second"
            }
        }

        binding.allDesksTab.setOnClickListener {
            if (activeTab == "second") {
                binding.onlyInLoadingTab.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.onlyInLoadingText.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.primary
                    )
                )
                binding.allDesksTab.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.primary
                    )
                )
                binding.allDesksText.setTextColor(ContextCompat.getColor(this, R.color.white))
                activeTab = "first"
            }
        }
    }

    override fun initViews() {
        super.initViews()
        ImmersionBar.with(this).statusBarColor(R.color.primary).init();
        recyclerView = binding.mainRecyclerView

        recyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        recyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = SPAN_COUNT,
                horizontalSpacing = resources.getDimensionPixelSize(R.dimen.d10),
                verticalSpacing = resources.getDimensionPixelSize(R.dimen.d10),
                includeEdge = false
            )
        )

        val adapter = UnloadingListTopItemAdapter()
        recyclerView.adapter = adapter

        adapter.setOnClickListener { item ->
            Log.d(TAG, "initViews: $item")
            val position = adapter.currentList.indexOfFirst { it.id == item.id }
            Log.d(TAG, "initViews: $position")
            viewModel.selectItem(position)
        }

        // 观察数据变化
        viewModel.itemList.observe(this) { items ->
            Log.d(TAG, "initViews: ${items}")
            adapter.submitList(items)
        }

    }

    companion object {
        const val TAG = "UnloadingListActivity"
    }
}