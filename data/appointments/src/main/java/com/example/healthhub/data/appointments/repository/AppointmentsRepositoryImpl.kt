package com.example.healthhub.data.appointments.repository

import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.AppointmentTimeframe
import com.example.healthhub.data.appointments.model.County
import com.example.healthhub.data.appointments.model.FilteredAppointment
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Service
import com.example.healthhub.data.appointments.model.Specialization
import com.example.healthhub.data.info.model.Location
import com.example.healthhub.network.api.model.AppointmentRequest
import com.example.healthhub.network.api.model.LocationDTO
import com.example.healthhub.network.api.model.ServiceDTO
import com.example.healthhub.network.api.model.SpecializationDTO
import com.example.healthhub.network.api.service.AppointmentsApi
import com.example.healthhub.network.api.service.CountiesApi
import com.example.healthhub.network.api.service.LocationsApi
import com.example.healthhub.network.api.service.MedicsApi
import com.example.healthhub.network.api.service.SpecializationsApi
import com.example.healthhub.network.resource.Resource
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class AppointmentsRepositoryImpl(
    private val appointmentsApi: AppointmentsApi,
    private val countiesApi: CountiesApi,
    private val locationsApi: LocationsApi,
    private val medicsApi: MedicsApi,
    private val specializationsApi: SpecializationsApi
) : AppointmentsRepository {

    override suspend fun getAppointments(
        usersInfo: UsersInfo,
        timeframe: AppointmentTimeframe
    ): Resource<List<Appointment>> {
        val resource = when (val childId = usersInfo.child?.id) {
            usersInfo.selectedUserId -> when (timeframe) {
                AppointmentTimeframe.PAST -> appointmentsApi.getChildPastAppointments(
                    parentId = usersInfo.parent.id,
                    childId = childId
                )


                AppointmentTimeframe.FUTURE -> appointmentsApi.getChildFutureAppointments(
                    parentId = usersInfo.parent.id,
                    childId = childId
                )
            }

            else -> when (timeframe) {
                AppointmentTimeframe.PAST -> {
                    appointmentsApi.getParentPastAppointments(usersInfo.parent.id)
                }

                AppointmentTimeframe.FUTURE -> {
                    appointmentsApi.getParentFutureAppointments(usersInfo.parent.id)
                }
            }
        }

        val appointmentDTOs = resource.payload?.innerAppointmentsDTO?.entities ?: emptyList()
        val appointments = appointmentDTOs.mapNotNull {
            val specializations = mapSpecializationDTOs(it.specializations)
            val specialization = mapSpecializationId(it.specializationId, it.specializations)

            Appointment(
                id = it.id ?: return@mapNotNull null,
                availableAppointmentId = it.availableAppointmentId ?: return@mapNotNull null,
                userId = it.userId ?: return@mapNotNull null,
                medic = mapMedicId(it.doctorId) ?: return@mapNotNull null,
                county = mapCountyId(it.countyId) ?: return@mapNotNull null,
                location = mapLocationId(it.locationId) ?: return@mapNotNull null,
                state = it.state ?: return@mapNotNull null,
                startDate = it.startDate ?: return@mapNotNull null,
                duration = it.duration ?: return@mapNotNull null,
                price = it.price ?: return@mapNotNull null,
                specialization = specialization ?: return@mapNotNull null,
                childId = it.childId,
                specializations = specializations ?: return@mapNotNull null,
                isCancelable = timeframe == AppointmentTimeframe.FUTURE
            )
        }

        return Resource(appointments, resource.status)
    }

    override suspend fun getSpecializations(): Resource<List<Specialization>> {
        val resource = specializationsApi.getSpecializations()
        val specializationDTOs = resource.payload?.innerSpecializationsDTO?.entities
        val specializations = mapSpecializationDTOs(specializationDTOs) ?: emptyList()
        return Resource(specializations, resource.status)
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

    override suspend fun getCounties(): Resource<List<County>> {
        val resource = countiesApi.getCounties()
        val countyDTOs = resource.payload?.innerCountiesDTO?.entities
        val counties = countyDTOs?.mapNotNull { countyDTO ->
            County(
                id = countyDTO.id ?: return@mapNotNull null,
                name = countyDTO.name ?: return@mapNotNull null,
            )
        }

        return Resource(counties, resource.status)
    }

    override suspend fun getMedicsBySpecializationAndCounty(
        specializationId: Int,
        countyId: Int
    ): Resource<List<Medic>> {
        val resource = appointmentsApi.getMedicBySpecializationsAndCounty(
            mapOf(
                "specializationId" to specializationId,
                "countyId" to countyId,
            )
        )

        val medicsDTOs = resource.payload?.innerFilteredMedics?.entities
        val medics = medicsDTOs?.mapNotNull { medicDTO ->
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

    override suspend fun filterAppointments(
        specializationName: String,
        countyName: String,
        startDateMillis: Long,
        userId: Int,
        medicId: Int?,
        locationId: Int?
    ): Resource<List<FilteredAppointment>> {
        val resource = appointmentsApi.filterAppointments(
            specializationName = specializationName,
            countyName = countyName,
            startDateMillis = startDateMillis,
            userId = userId,
            medicId = medicId,
            locationId = locationId
        )

        val appointmentDTOs = resource.payload?.innerFilteredAppointments?.entities ?: emptyList()
        val appointments = appointmentDTOs.mapNotNull {
            FilteredAppointment(
                id = it.id ?: return@mapNotNull null,
                doctorFullName = it.doctorFullName ?: return@mapNotNull null,
                doctorId = it.doctorId ?: return@mapNotNull null,
                ranking = it.ranking ?: return@mapNotNull null,
                specializationId = it.details?.specializationId ?: return@mapNotNull null,
                specializationName = it.details?.specializationName ?: return@mapNotNull null,
                services = mapServiceDTOs(it.details?.services) ?: return@mapNotNull null,
                location = Location(
                    id = it.locationEntity?.id ?: return@mapNotNull null,
                    name = it.locationEntity?.name ?: return@mapNotNull null,
                    address = it.locationEntity?.address ?: return@mapNotNull null,
                    latitude = it.locationEntity?.latitude ?: return@mapNotNull null,
                    longitude = it.locationEntity?.longitude ?: return@mapNotNull null,
                ),
                county = County(
                    id = it.locationEntity?.county?.id ?: return@mapNotNull null,
                    name = it.locationEntity?.county?.name ?: return@mapNotNull null,
                ),
                dateMillis = it.dateMillis ?: return@mapNotNull null
            )
        }

        return Resource(appointments, resource.status)
    }

    override suspend fun createAppointment(request: AppointmentRequest) {
        val json = Gson().toJson(request)
        appointmentsApi.createAppointment(
            json.toRequestBody("application/json".toMediaTypeOrNull())
        )
    }

    override suspend fun cancelAppointment(appointmentId: Int) {
        appointmentsApi.cancelAppointment(appointmentId)
    }

    private fun mapServiceDTOs(serviceDTOs: List<ServiceDTO>?): List<Service>? {
        return serviceDTOs?.mapNotNull { serviceDTO ->
            Service(
                id = serviceDTO.id ?: return@mapNotNull null,
                name = serviceDTO.name ?: return@mapNotNull null,
                description = serviceDTO.description ?: return@mapNotNull null,
                price = serviceDTO.price ?: return@mapNotNull null,
                discountedPrice = serviceDTO.discountedPrice ?: return@mapNotNull null,
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

    private suspend fun mapCountyId(countyId: Int?): County? {
        val countyDTOs = countiesApi.getCounties().payload?.innerCountiesDTO?.entities
        val countyDTO = countyDTOs?.firstOrNull { it.id == countyId }
        return County(
            id = countyDTO?.id ?: return null,
            name = countyDTO.name ?: return null
        )
    }

    private suspend fun mapLocationId(locationId: Int?): Location? {
        val locationDTOs = locationsApi.getLocations().payload?.innerLocationsDTO?.locationEntities
        val locationDTO = locationDTOs?.firstOrNull { it.id == locationId }
        return Location(
            id = locationDTO?.id ?: return null,
            name = locationDTO.name ?: return null,
            address = locationDTO.address ?: return null,
            latitude = locationDTO.latitude ?: return null,
            longitude = locationDTO.longitude ?: return null,
        )
    }

    private suspend fun mapMedicId(medicId: Int?): Medic? {
        val medicDTOs = medicsApi.getMedics().payload?.innerMedicsDTO?.entities
        val medicDTO = medicDTOs?.firstOrNull { it.id == medicId }
        return Medic(
            id = medicDTO?.id ?: return null,
            name = medicDTO.name ?: return null,
            ranking = medicDTO.ranking ?: return null,
            specializations = mapSpecializationDTOs(medicDTO.specializations) ?: return null,
            services = mapServiceDTOs(medicDTO.services) ?: return null,
            locations = mapLocationDTOs(medicDTO.locations) ?: return null,
            county = County(
                id = medicDTO.county?.id ?: return null,
                name = medicDTO.county?.name ?: return null,
            )
        )
    }

    private fun mapSpecializationId(
        specializationId: Int?,
        specializationDTOs: List<SpecializationDTO>?
    ): Specialization? {
        val specializationDTO = specializationDTOs?.firstOrNull {
            it.id == specializationId
        } ?: specializationDTOs?.firstOrNull()

        return Specialization(
            id = specializationDTO?.id ?: return null,
            name = specializationDTO.name ?: return null,
            description = specializationDTO.description ?: return null,
            services = mapServiceDTOs(specializationDTO.services) ?: return null
        )
    }
}
