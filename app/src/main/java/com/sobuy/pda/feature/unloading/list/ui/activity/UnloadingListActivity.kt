package com.sobuy.pda.feature.unloading.list.ui.activity

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sobuy.pda.R
import com.sobuy.pda.feature.unloading.list.ui.viewmodel.UnloadingListViewModel
import com.sobuy.pda.core.base.activity.BaseViewModelActivity
import com.sobuy.pda.core.utils.GridSpacingItemDecoration
import com.sobuy.pda.databinding.ActivityUnloadingListBinding
import com.sobuy.pda.feature.unloading.detail.ui.activity.UnloadingDetailActivity
import com.sobuy.pda.feature.unloading.list.ui.adapter.UnloadingListChildrenItemAdapter
import com.sobuy.pda.feature.unloading.list.ui.adapter.UnloadingListTopItemAdapter
import kotlin.getValue

class UnloadingListActivity :
    BaseViewModelActivity<ActivityUnloadingListBinding>(ActivityUnloadingListBinding::inflate) {

    private val viewModel: UnloadingListViewModel by viewModels()

    private lateinit var topRecyclerView: RecyclerView
    private lateinit var childRecyclerView: RecyclerView

    private var activeTab: String = TAB_FIRST

    override fun initListeners() {
        super.initListeners()
        binding.onlyInLoadingTab.setOnClickListener {
            if (activeTab == TAB_FIRST) {
                setTabActive(
                    activeTab = binding.onlyInLoadingTab,
                    activeText = binding.onlyInLoadingText,
                    inactiveTab = binding.allDesksTab,
                    inactiveText = binding.allDesksText
                )
                activeTab = TAB_SECOND
                binding.asyncButton.visibility = View.GONE
            }
        }

        binding.allDesksTab.setOnClickListener {
            if (activeTab == TAB_SECOND) {
                setTabActive(
                    activeTab = binding.allDesksTab,
                    activeText = binding.allDesksText,
                    inactiveTab = binding.onlyInLoadingTab,
                    inactiveText = binding.onlyInLoadingText
                )
                activeTab = TAB_FIRST
                binding.asyncButton.visibility = View.VISIBLE
            }
        }
    }

    override fun initViews() {
        super.initViews()
        topRecyclerView = binding.topRecyclerView
        topRecyclerView.itemAnimator = null
        var d10 = resources.getDimensionPixelSize(R.dimen.d10)
        topRecyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        topRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = SPAN_COUNT,
                horizontalSpacing = d10,
                verticalSpacing = d10,
                includeEdge = false
            )
        )

        val adapter = UnloadingListTopItemAdapter()
        topRecyclerView.adapter = adapter.apply {
            setOnClickListener { item ->
                val position = adapter.currentList.indexOfFirst { it.id == item.id }
                viewModel.selectItem(position)
            }
        }

        viewModel.itemList.observe(this) { items ->
            // 控制空状态显示
            if (items.isEmpty()) {
                binding.content.visibility = View.GONE
                binding.emptyStateView.visibility = View.VISIBLE
            } else {
                binding.content.visibility = View.VISIBLE
                binding.emptyStateView.visibility = View.GONE
            }

            adapter.submitList(items)
        }

        childRecyclerView = binding.childRecyclerView
//        childRecyclerView.itemAnimator = null
        childRecyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        childRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = SPAN_COUNT,
                horizontalSpacing = d10,
                verticalSpacing = d10,
                includeEdge = false
            )
        )

        val childrenItemAdapter = UnloadingListChildrenItemAdapter()
        childRecyclerView.adapter = childrenItemAdapter.apply {
            setOnClickListener { item ->
                Log.d(TAG, "jumperDetail: $item")
                startActivity(UnloadingDetailActivity::class.java)
            }
        }

        // Key: Observe the changes in the selected sub-data and update the sub-item list in real time
        viewModel.selectedChildren.observe(this) { selectedChildren ->

            if (selectedChildren.isEmpty()) {
                binding.childRecyclerView.visibility = View.GONE
            } else {
                binding.childRecyclerView.visibility = View.VISIBLE
            }
            childrenItemAdapter.submitList(selectedChildren)
        }
    }

    private val colorPrimary by lazy { ContextCompat.getColor(this, R.color.primary) }
    private val colorWhite by lazy { ContextCompat.getColor(this, R.color.white) }

    private fun setTabActive(
        activeTab: View,
        activeText: TextView,
        inactiveTab: View,
        inactiveText: TextView
    ) {
        activeTab.setBackgroundColor(colorPrimary)
        activeText.setTextColor(colorWhite)
        inactiveTab.setBackgroundColor(colorWhite)
        inactiveText.setTextColor(colorPrimary)
    }

    companion object {
        const val TAG = "UnloadingListActivity"
        private const val SPAN_COUNT = 3
        private const val TAB_FIRST = "first"
        private const val TAB_SECOND = "second"
    }
}