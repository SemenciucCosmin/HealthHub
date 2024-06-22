package com.example.healthhub.di

import com.example.healthhub.data.di.dataModule
import com.example.healthhub.presentation.appointments.di.featureAppointmentsModule
import com.example.healthhub.presentation.authentication.di.featureAuthenticationModule
import com.example.healthhub.presentation.home.di.featureHomeModule
import com.example.healthhub.presentation.info.di.featureInfoModule
import com.example.healthhub.presentation.medicalfile.di.featureMedicalFileModule

val libraryModules = listOf(
    dataModule,
    featureAppointmentsModule,
    featureAuthenticationModule,
    featureHomeModule,
    featureInfoModule,
    featureMedicalFileModule
)
