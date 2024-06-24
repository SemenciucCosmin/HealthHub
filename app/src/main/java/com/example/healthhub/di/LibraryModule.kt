package com.example.healthhub.di

import com.example.healthhub.data.account.di.dataAccountModule
import com.example.healthhub.data.authentication.di.dataAuthenticationModule
import com.example.healthhub.data.di.dataModule
import com.example.healthhub.data.info.di.dataInfoModule
import com.example.healthhub.data.preferences.di.dataPreferencesModule
import com.example.healthhub.domain.account.di.domainAccountModule
import com.example.healthhub.network.di.networkModule
import com.example.healthhub.presentation.account.di.featureAccountModule
import com.example.healthhub.presentation.appointments.di.featureAppointmentsModule
import com.example.healthhub.presentation.authentication.di.featureAuthenticationModule
import com.example.healthhub.presentation.home.di.featureHomeModule
import com.example.healthhub.presentation.info.di.featureInfoModule
import com.example.healthhub.presentation.medicalfile.di.featureMedicalFileModule

val libraryModules = listOf(
    dataModule,
    dataAccountModule,
    dataAuthenticationModule,
    dataInfoModule,
    dataPreferencesModule,
    domainAccountModule,
    featureAccountModule,
    featureAppointmentsModule,
    featureAuthenticationModule,
    featureHomeModule,
    featureInfoModule,
    featureMedicalFileModule,
    networkModule
)
