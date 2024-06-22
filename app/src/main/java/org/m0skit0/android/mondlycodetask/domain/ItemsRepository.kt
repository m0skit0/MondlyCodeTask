package org.m0skit0.android.mondlycodetask.domain

import org.m0skit0.android.mondlycodetask.data.ItemsDTO
import org.m0skit0.android.mondlycodetask.data.ItemsService

class ItemsRepository(
    private val itemsService: ItemsService,
) {
    suspend fun items(): List<ItemsDTO> = itemsService.items()
}
