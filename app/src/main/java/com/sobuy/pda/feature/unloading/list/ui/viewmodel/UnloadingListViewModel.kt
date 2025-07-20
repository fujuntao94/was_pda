package com.sobuy.pda.feature.unloading.list.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sobuy.pda.feature.unloading.list.data.model.UnloadingListContent
import kotlinx.coroutines.flow.MutableSharedFlow

class UnloadingListViewModel: ViewModel() {
    private val _data = MutableSharedFlow<UnloadingListContent>()
}