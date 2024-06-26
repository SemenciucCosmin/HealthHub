package com.example.healthhub.data.authentication.di

import com.example.healthhub.data.authentication.repository.AuthenticationRepository
import com.example.healthhub.data.authentication.repository.AuthenticationRepositoryImpl
import org.koin.dsl.module

val dataAuthenticationModule = module {
    factory<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}
