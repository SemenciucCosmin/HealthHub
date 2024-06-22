package com.example.healthhub.data.preferences.di

import com.example.healthhub.data.preferences.PreferencesDataStore
import com.example.healthhub.data.preferences.repository.PreferencesRepository
import com.example.healthhub.data.preferences.repository.PreferencesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataPreferencesModule = module {
    single { PreferencesDataStore(androidContext()) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
}
