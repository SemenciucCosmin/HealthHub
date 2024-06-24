package com.example.healthhub.presentation.info.di

import com.example.healthhub.presentation.info.viewmodel.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureInfoModule = module {
    viewModelOf(::InfoViewModel)
}
