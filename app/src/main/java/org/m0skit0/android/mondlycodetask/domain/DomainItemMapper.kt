package org.m0skit0.android.mondlycodetask.domain

import org.m0skit0.android.mondlycodetask.data.ItemsDTO

interface DomainItemMapper {
    fun map(itemsDTO: ItemsDTO): List<Item>
}

class DomainItemMapperImpl : DomainItemMapper {
    override fun map(itemsDTO: ItemsDTO): List<Item> =
        itemsDTO.dataCollection.map {
            Item(
                id = it.item.id,
                name = it.item.attributes.name,
                description = it.item.attributes.description,
                imageUrl = it.item.attributes.imageInfo.imageUrl,
            )
        }
}
