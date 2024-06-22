package com.example.healthhub.data.di

import com.example.healthhub.data.preferences.PreferencesDataStore
import com.example.healthhub.data.preferences.PreferencesRepository
import com.example.healthhub.data.preferences.PreferencesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { PreferencesDataStore(androidContext()) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
}
