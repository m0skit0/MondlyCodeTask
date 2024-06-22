package org.m0skit0.android.mondlycodetask.domain

typealias GetItemsUseCase = suspend () -> List<Item>

fun getItemsUseCase(itemsRepository: ItemsRepository): GetItemsUseCase = {
    itemsRepository.items()
}
