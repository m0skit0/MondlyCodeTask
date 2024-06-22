package org.m0skit0.android.mondlycodetask.di

import org.koin.dsl.module
import org.m0skit0.android.mondlycodetask.domain.ItemsRepository
import org.m0skit0.android.mondlycodetask.domain.ItemsRepositoryImpl

val domainModule = module {

    single<ItemsRepository> {
        ItemsRepositoryImpl(get())
    }
}
