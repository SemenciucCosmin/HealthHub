package com.example.healthhub.domain.account.di

import com.example.healthhub.domain.account.GetUsersInfoUseCase
import com.example.healthhub.domain.account.SetUserInformationUseCase
import org.koin.dsl.module

val domainAccountModule = module {
    factory { GetUsersInfoUseCase(get()) }
    factory { SetUserInformationUseCase(get()) }
}
