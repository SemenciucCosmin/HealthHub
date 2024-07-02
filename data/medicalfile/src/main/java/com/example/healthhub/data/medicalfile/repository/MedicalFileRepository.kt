package com.example.healthhub.data.medicalfile.repository

import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.network.resource.Resource

interface MedicalFileRepository {
    suspend fun getMedics(): Resource<List<Medic>>
}
