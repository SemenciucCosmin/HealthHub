package com.example.healthhub.feature.appointments.di

import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

object AppointmentsScope {

    const val ID = "APPOINTMENTS_SCOPE"

    private lateinit var scope: Scope

    fun create(koin: Koin) {
        // Delete old scope.
        delete(koin)

        // Create scope.
        scope = koin.createScope(ID, named(ID))
    }

    fun delete(koin: Koin) {
        koin.getScopeOrNull(ID)?.close()
    }
}