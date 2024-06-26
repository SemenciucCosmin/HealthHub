package com.example.healthhub.di

import com.example.healthhub.data.account.di.dataAccountModule
import com.example.healthhub.data.appointments.di.dataAppointmentsModule
import com.example.healthhub.data.authentication.di.dataAuthenticationModule
import com.example.healthhub.domain.account.di.domainAccountModule
import com.example.healthhub.data.home.di.dataHomeModule
import com.example.healthhub.data.info.di.dataInfoModule
import com.example.healthhub.data.medicalfile.di.dataMedicalFileModule
import com.example.healthhub.network.di.networkModule
import com.example.healthhub.feature.account.di.featureAccountModule
import com.example.healthhub.feature.appointments.di.featureAppointmentsModule
import com.example.healthhub.feature.authentication.di.featureAuthenticationModule
import com.example.healthhub.feature.home.di.featureHomeModule
import com.example.healthhub.info.di.featureInfoModule
import com.example.healthhub.feature.medicalfile.di.featureMedicalFileModule

val libraryModules = listOf(
    dataAccountModule,
    dataAppointmentsModule,
    dataAuthenticationModule,
    dataHomeModule,
    dataInfoModule,
    dataMedicalFileModule,
    domainAccountModule,
    featureAccountModule,
    featureAppointmentsModule,
    featureAuthenticationModule,
    featureHomeModule,
    featureInfoModule,
    featureMedicalFileModule,
    networkModule
)
