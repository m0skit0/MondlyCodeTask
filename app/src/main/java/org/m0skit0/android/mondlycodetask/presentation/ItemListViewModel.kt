package org.m0skit0.android.mondlycodetask.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.m0skit0.android.mondlycodetask.domain.GetItemsUseCase
import org.m0skit0.android.mondlycodetask.utils.Items

class ItemListViewModel(
    private val getItemsUseCase: GetItemsUseCase,
) : ViewModel() {

    val items: State<UiState<Items>> by lazy { _items }

    private val _items: MutableState<UiState<Items>> by lazy {
        mutableStateOf(UiState.Loading)
    }

    suspend fun loadItems() {
        _items.value = UiState.Loading
        getItemsUseCase().fold(
            onSuccess = { items -> _items.value = UiState.Success(items) },
            onFailure = { error -> _items.value = UiState.Error(error.message ?: "Unknown error") }
        )
    }
}
