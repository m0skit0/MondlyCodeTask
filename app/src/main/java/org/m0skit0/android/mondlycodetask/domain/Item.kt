package org.m0skit0.android.mondlycodetask.domain

import org.m0skit0.android.mondlycodetask.utils.Id
import org.m0skit0.android.mondlycodetask.utils.Url

data class Item(
    val id: Id,
    val name: String,
    val description: String,
    val imageUrl: Url,
)
