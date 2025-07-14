package com.sobuy.pda.component.unloading.list

import androidx.lifecycle.ViewModel
import com.sobuy.pda.component.unloading.list.data.model.UnloadingListContent
import kotlinx.coroutines.flow.MutableSharedFlow

class UnloadingListViewModel: ViewModel() {
    private val _data = MutableSharedFlow<UnloadingListContent>()
}