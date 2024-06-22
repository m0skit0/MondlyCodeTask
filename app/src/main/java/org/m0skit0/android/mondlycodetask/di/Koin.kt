package org.m0skit0.android.mondlycodetask.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun Context.initializeKoin() {
    startKoin {
        androidContext(this@initializeKoin.applicationContext)
        modules(dataModule)
    }
}
