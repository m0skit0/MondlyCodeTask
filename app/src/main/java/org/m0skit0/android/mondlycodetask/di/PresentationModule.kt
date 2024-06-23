package org.m0skit0.android.mondlycodetask.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.m0skit0.android.mondlycodetask.presentation.ItemListViewModel

val presentationModule = module {
    viewModelOf(::ItemListViewModel)
}
