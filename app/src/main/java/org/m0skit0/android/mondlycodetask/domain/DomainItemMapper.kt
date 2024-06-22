package org.m0skit0.android.mondlycodetask.domain

import org.m0skit0.android.mondlycodetask.data.ItemsDTO

interface DomainItemMapper {
    fun map(itemsDTO: ItemsDTO): List<Item>
}
