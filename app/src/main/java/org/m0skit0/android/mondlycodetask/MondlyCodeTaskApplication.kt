package org.m0skit0.android.mondlycodetask

import android.app.Application
import org.m0skit0.android.mondlycodetask.di.initializeKoin

class MondlyCodeTaskApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}
