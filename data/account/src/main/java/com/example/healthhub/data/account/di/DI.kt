package com.example.healthhub.data.account.di

import com.example.healthhub.data.account.preferences.PreferencesDataStore
import com.example.healthhub.data.account.repository.AccountMockedRepositoryImpl
import com.example.healthhub.data.account.repository.AccountRepository
import com.example.healthhub.data.account.repository.AccountRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataAccountModule = module {
    single { PreferencesDataStore(androidContext()) }
    factory<AccountRepository> { AccountRepositoryImpl(get(), get()) }
//    factory<AccountRepository> { AccountMockedRepositoryImpl(get()) }
}
