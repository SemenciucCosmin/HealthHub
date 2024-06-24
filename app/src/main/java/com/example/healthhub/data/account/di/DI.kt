package com.example.healthhub.data.account.di

import com.example.healthhub.data.account.repository.AccountRepository
import com.example.healthhub.data.account.repository.AccountRepositoryImpl
import org.koin.dsl.module

val dataAccountModule = module {
    factory<AccountRepository> { AccountRepositoryImpl(get(), get()) }
}
