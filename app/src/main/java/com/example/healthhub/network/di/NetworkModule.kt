package com.example.healthhub.network.di

import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.network.call.CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://localhost:8080/api/v1/medicalclinicproject")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CallAdapterFactory())
            .build()
    }

    factory { get<Retrofit>().create(AuthenticationApi::class.java) }
}