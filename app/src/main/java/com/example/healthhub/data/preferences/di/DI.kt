package com.example.healthhub.data.preferences.di

import com.example.healthhub.data.preferences.PreferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataPreferencesModule = module {
    single { PreferencesDataStore(androidContext()) }
}
