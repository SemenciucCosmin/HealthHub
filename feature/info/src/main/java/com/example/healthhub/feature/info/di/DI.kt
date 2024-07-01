package com.example.healthhub.info.di

import com.example.healthhub.feature.info.viewmodel.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureInfoModule = module {
    viewModelOf(::InfoViewModel)
}
