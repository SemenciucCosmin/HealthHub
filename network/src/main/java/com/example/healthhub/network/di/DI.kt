package com.example.healthhub.network.di

import com.example.healthhub.network.api.service.AccountApi
import com.example.healthhub.network.api.service.AppointmentsApi
import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.network.api.service.CountiesApi
import com.example.healthhub.network.api.service.LocationsApi
import com.example.healthhub.network.api.service.MedicsApi
import com.example.healthhub.network.api.service.SpecializationsApi
import com.example.healthhub.network.api.service.SubscriptionsApi
import com.example.healthhub.network.call.CallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.time.temporal.TemporalUnit
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.100.97:8080")
            .addCallAdapterFactory(CallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().apply {
                    readTimeout(30, TimeUnit.SECONDS)
                    connectTimeout(30, TimeUnit.SECONDS)
                    connectTimeout(30, TimeUnit.SECONDS)
                }.build()
            )
            .build()
    }

    factory { get<Retrofit>().create(AccountApi::class.java) }
    factory { get<Retrofit>().create(AppointmentsApi::class.java) }
    factory { get<Retrofit>().create(AuthenticationApi::class.java) }
    factory { get<Retrofit>().create(CountiesApi::class.java) }
    factory { get<Retrofit>().create(LocationsApi::class.java) }
    factory { get<Retrofit>().create(MedicsApi::class.java) }
    factory { get<Retrofit>().create(SpecializationsApi::class.java) }
    factory { get<Retrofit>().create(SubscriptionsApi::class.java) }
}