package com.example.healthhub.data.medicalfile.di

import com.example.healthhub.data.medicalfile.repository.MedicalFileRepository
import com.example.healthhub.data.medicalfile.repository.MedicalFileRepositoryImpl
import org.koin.dsl.module

val dataMedicalFileModule = module {
    factory<MedicalFileRepository> { MedicalFileRepositoryImpl(get()) }
}