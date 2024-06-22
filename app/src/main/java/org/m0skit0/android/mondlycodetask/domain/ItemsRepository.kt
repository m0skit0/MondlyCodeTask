package org.m0skit0.android.mondlycodetask.domain

import org.m0skit0.android.mondlycodetask.data.ItemsDTO
import org.m0skit0.android.mondlycodetask.data.ItemsService

interface ItemsRepository {
    suspend fun items(): List<ItemsDTO>
}

class ItemsRepositoryImpl(
    private val itemsService: ItemsService,
) : ItemsRepository {

    // Trivial caching mechanism to avoid multiple network calls
    private var cachedItems: List<ItemsDTO> = emptyList()

    override suspend fun items(): List<ItemsDTO> {
        if (cachedItems.isEmpty()) {
            cachedItems = itemsService.items()
        }
        return cachedItems
    }
}
