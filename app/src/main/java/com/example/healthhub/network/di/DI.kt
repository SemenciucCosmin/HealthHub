package com.example.healthhub.network.di

import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.network.call.CallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addCallAdapterFactory(CallAdapterFactory())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            ).build()
    }

    factory { get<Retrofit>().create(AuthenticationApi::class.java) }
}