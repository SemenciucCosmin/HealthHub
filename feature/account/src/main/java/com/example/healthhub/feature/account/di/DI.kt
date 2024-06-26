package com.example.healthhub.feature.account.di

import com.example.healthhub.feature.account.viewmodel.AccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureAccountModule = module {
    viewModelOf(::AccountViewModel)
}
