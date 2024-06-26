package com.example.healthhub.data.home.di

import com.example.healthhub.data.home.repository.HomeRepository
import com.example.healthhub.data.home.repository.HomeRepositoryImpl
import org.koin.dsl.module

val dataHomeModule = module {
    factory<HomeRepository> { HomeRepositoryImpl(get()) }
}