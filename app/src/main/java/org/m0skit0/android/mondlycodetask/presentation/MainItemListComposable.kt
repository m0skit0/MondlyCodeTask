package org.m0skit0.android.mondlycodetask.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import org.m0skit0.android.mondlycodetask.R
import org.m0skit0.android.mondlycodetask.domain.Item
import org.m0skit0.android.mondlycodetask.presentation.theme.Pink40
import org.m0skit0.android.mondlycodetask.presentation.theme.Purple80
import org.m0skit0.android.mondlycodetask.utils.coilImageLoader

@Composable
fun MainItemListComposable(
    viewModel: ItemListViewModel = koinViewModel(),
) {
    val state = remember { viewModel.items }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple80)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        when (val uiState = state.value) {
            is UiState.Loading -> Loading()
            is UiState.Success -> ItemListComposable(items = uiState.data)
            is UiState.Error -> Error(uiState)
        }
    }
}

@Composable
private fun ItemListComposable(items: List<Item>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = items) { ItemComposable(it) }
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
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ItemImage(item = item)
        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
            ItemName(item = item)
            Text(text = item.description)
        }
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

// TODO Can be done nicer (e.g. snackbar with a retry button)
@Composable
private fun Error(error: UiState.Error) {
    Text(text = "Error: ${error.message}")
}

@Composable
private fun ItemImage(item: Item) {
    AsyncImage(
        model = item.imageUrl,
        contentDescription = null,
        imageLoader = coilImageLoader(),
        modifier = Modifier
            .border(width = 3.dp, color = Pink40)
            .shadow(elevation = 6.dp),
        error = painterResource(id = R.drawable.placeholder),
    )
}

@Composable
private fun ItemName(item: Item) {
    Text(
        text = item.name,
        fontSize = 20.sp,
    )
}
