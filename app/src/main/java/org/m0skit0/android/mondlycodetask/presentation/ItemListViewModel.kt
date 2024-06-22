package org.m0skit0.android.mondlycodetask.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.m0skit0.android.mondlycodetask.domain.GetItemsUseCase
import org.m0skit0.android.mondlycodetask.utils.Items

class ItemListViewModel(
    private val getItemsUseCase: GetItemsUseCase,
) : ViewModel() {

    val items: Flow<UiState<Items>> by lazy { _items }

    private val _items: MutableStateFlow<UiState<Items>> by lazy {
        MutableStateFlow(UiState.Loading)
    }

    suspend fun loadItems() {
        _items.value = UiState.Loading
        getItemsUseCase().fold(
            onSuccess = { items -> _items.value = UiState.Success(items) },
            onFailure = { error -> _items.value = UiState.Error(error.message ?: "Unknown error") }
        )
    }
}
