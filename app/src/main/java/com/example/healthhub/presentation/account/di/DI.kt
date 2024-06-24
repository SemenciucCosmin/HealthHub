package com.example.healthhub.presentation.account.di

import com.example.healthhub.presentation.account.viewmodel.AccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureAccountModule = module {
    viewModelOf(::AccountViewModel)
}
