package com.example.healthhub.feature.medicalfile.di

import com.example.healthhub.feature.medicalfile.viewmodel.MedicalFileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureMedicalFileModule = module {
    viewModelOf(::MedicalFileViewModel)
}
