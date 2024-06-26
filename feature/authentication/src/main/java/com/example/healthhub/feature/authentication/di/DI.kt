package com.example.healthhub.feature.authentication.di

import com.example.healthhub.feature.authentication.viewmodel.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureAuthenticationModule = module {
    viewModelOf(::AuthenticationViewModel)
}
