package com.example.healthhub.domain.account.di

import com.example.healthhub.domain.account.GetUserUseCase
import com.example.healthhub.domain.account.SetUserInformationUseCase
import org.koin.dsl.module

val domainAccountModule = module {
    factory { GetUserUseCase(get()) }
    factory { SetUserInformationUseCase(get()) }
}
