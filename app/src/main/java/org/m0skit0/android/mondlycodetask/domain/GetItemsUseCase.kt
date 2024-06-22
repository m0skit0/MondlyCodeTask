package org.m0skit0.android.mondlycodetask.domain

typealias GetItemsUseCase = suspend () -> Result<List<Item>>

fun getItemsUseCase(itemsRepository: ItemsRepository): GetItemsUseCase = {
    itemsRepository.items()
}
