package com.example.healthhub.data.medicalfile.repository

import com.example.healthhub.data.appointments.model.County
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Service
import com.example.healthhub.data.appointments.model.Specialization
import com.example.healthhub.data.info.model.Location
import com.example.healthhub.network.api.model.LocationDTO
import com.example.healthhub.network.api.model.ServiceDTO
import com.example.healthhub.network.api.model.SpecializationDTO
import com.example.healthhub.network.api.service.MedicsApi
import com.example.healthhub.network.resource.Resource

class MedicalFileRepositoryImpl(
    private val medicsApi: MedicsApi
) : MedicalFileRepository {

    override suspend fun getMedics(): Resource<List<Medic>> {
        val resource = medicsApi.getMedics()
        val medicDTOs = resource.payload?.innerMedicsDTO?.entities
        val medics = medicDTOs?.mapNotNull { medicDTO ->
            val specializations = mapSpecializationDTOs(medicDTO.specializations)

            Medic(
                id = medicDTO.id ?: return@mapNotNull null,
                name = medicDTO.name ?: return@mapNotNull null,
                ranking = medicDTO.ranking ?: return@mapNotNull null,
                specializations = specializations ?: return@mapNotNull null,
                services = mapServiceDTOs(medicDTO.services) ?: return@mapNotNull null,
                locations = mapLocationDTOs(medicDTO.locations) ?: return@mapNotNull null,
                county = County(
                    id = medicDTO.county?.id ?: return@mapNotNull null,
                    name = medicDTO.county?.name ?: return@mapNotNull null,
                )
            )
        }

        return Resource(medics, resource.status)
    }

    private fun mapSpecializationDTOs(
        specializationDTOs: List<SpecializationDTO>?
    ): List<Specialization>? {
        return specializationDTOs?.mapNotNull { specializationDTO ->
            Specialization(
                id = specializationDTO.id ?: return@mapNotNull null,
                name = specializationDTO.name ?: return@mapNotNull null,
                description = specializationDTO.description ?: return@mapNotNull null,
                services = mapServiceDTOs(specializationDTO.services) ?: return@mapNotNull null
            )
        }
    }

    private fun mapServiceDTOs(serviceDTOs: List<ServiceDTO>?): List<Service>? {
        return serviceDTOs?.mapNotNull { serviceDTO ->
            Service(
                id = serviceDTO.id ?: return@mapNotNull null,
                name = serviceDTO.name ?: return@mapNotNull null,
                description = serviceDTO.description ?: return@mapNotNull null,
                price = serviceDTO.price ?: return@mapNotNull null,
                duration = serviceDTO.duration ?: return@mapNotNull null,
            )
        }
    }

    private fun mapLocationDTOs(locationDTOs: List<LocationDTO>?): List<Location>? {
        return locationDTOs?.mapNotNull { locationDTO ->
            Location(
                id = locationDTO.id ?: return@mapNotNull null,
                name = locationDTO.name ?: return@mapNotNull null,
                address = locationDTO.address ?: return@mapNotNull null,
                latitude = locationDTO.latitude ?: return@mapNotNull null,
                longitude = locationDTO.longitude ?: return@mapNotNull null,
            )
        }
    }
}
