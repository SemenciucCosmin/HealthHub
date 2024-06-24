package com.example.healthhub.presentation.account.di

import com.example.healthhub.presentation.authentication.viewmodel.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureAuthenticationModule = module {
    viewModelOf(::AuthenticationViewModel)
}
