package org.m0skit0.android.mondlycodetask.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import org.m0skit0.android.mondlycodetask.domain.Item

@Composable
fun MainItemListComposable(
    viewModel: ItemListViewModel = koinViewModel(),
) {
    val state = remember { viewModel.items }
    when (val uiState = state.value) {
        is UiState.Loading -> Text(text = "Loading")
        is UiState.Success -> ItemListComposable(items = uiState.data)
        is UiState.Error -> Text(text = "Error: ${uiState.message}")
    }
}

@Composable
private fun ItemListComposable(items: List<Item>) {
    Column {
        items.forEach { item ->
            ItemComposable(item)
        }
    }
}

@Composable
@Preview
private fun ItemListComposablePreview() {
    ItemListComposable(
        listOf(
            Item(
                id = "id1",
                name = "name1",
                description = "description1",
                imageUrl = "imageUrl1",
            ),
            Item(
                id = "id2",
                name = "name2",
                description = "description2",
                imageUrl = "imageUrl2",
            ),
        )
    )
}

@Composable
private fun ItemComposable(item: Item) {
    Row {
        Text(text = item.id)
        Text(text = item.description)
        Text(text = item.imageUrl)
    }
}

@Composable
@Preview
private fun ItemComposablePreview() {
    ItemComposable(
        Item(
            id = "id",
            name = "name",
            description = "description",
            imageUrl = "imageUrl",
        )
    )
}
