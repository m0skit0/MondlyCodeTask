package org.m0skit0.android.mondlycodetask.domain

import org.m0skit0.android.mondlycodetask.data.ItemsService

interface ItemsRepository {
    suspend fun items(): Result<List<Item>>
}

class ItemsRepositoryImpl(
    private val itemsService: ItemsService,
    private val domainItemMapper: DomainItemMapper,
) : ItemsRepository {

    // Trivial caching mechanism to avoid multiple network calls
    private var cachedItems: List<Item> = emptyList()

    override suspend fun items(): Result<List<Item>> = runCatching {
        if (cachedItems.isEmpty()) {
            val items = itemsService.items()
            cachedItems = domainItemMapper.map(items)
        }
        cachedItems
    }
}
