package com.example.healthhub.data.info.di

import com.example.healthhub.data.info.repository.InfoRepository
import com.example.healthhub.data.info.repository.InfoRepositoryImpl
import org.koin.dsl.module

val dataInfoModule = module {
    factory<InfoRepository> { InfoRepositoryImpl(get()) }
}
