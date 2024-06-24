package com.example.healthhub.network.di

import com.example.healthhub.network.api.service.AccountApi
import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.network.api.service.LocationsApi
import com.example.healthhub.network.call.CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.100.97:8080")
            .addCallAdapterFactory(CallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(AccountApi::class.java) }
    factory { get<Retrofit>().create(AuthenticationApi::class.java) }
    factory { get<Retrofit>().create(LocationsApi::class.java) }
}