package com.example.healthhub.presentation.app

import android.app.Application
import com.example.healthhub.di.libraryModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HealthHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@HealthHubApplication)
            modules(libraryModules)
        }
    }
}
