package com.example.healthhub.feature.home.di

import com.example.healthhub.feature.home.viewmodel.HomeViewModel
import com.example.healthhub.feature.home.viewmodel.SubscriptionDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureHomeModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SubscriptionDetailsViewModel)
}
