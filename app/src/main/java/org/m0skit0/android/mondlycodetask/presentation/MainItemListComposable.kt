package org.m0skit0.android.mondlycodetask.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.m0skit0.android.mondlycodetask.domain.Item

@Composable
fun MainItemListComposable(
    viewModel: ItemListViewModel = koinViewModel(),
) {
    val state = remember { viewModel.items }
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        when (val uiState = state.value) {
            is UiState.Loading -> Loading()
            is UiState.Success -> ItemListComposable(items = uiState.data)
            is UiState.Error -> Text(text = "Error: ${uiState.message}")
        }
    }
}

@Composable
private fun ItemListComposable(items: List<Item>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
        Text(text = item.name)
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

@Composable
private fun Loading() {
    CircularProgressIndicator()
}
