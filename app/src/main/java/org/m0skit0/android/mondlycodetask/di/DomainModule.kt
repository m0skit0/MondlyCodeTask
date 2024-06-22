package org.m0skit0.android.mondlycodetask.di

import org.koin.dsl.module
import org.m0skit0.android.mondlycodetask.domain.ItemsRepository

val domainModule = module {

    single {
        ItemsRepository(get())
    }
}
