package com.example.healthhub.data.appointments.repository

import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.AppointmentTimeframe
import com.example.healthhub.data.appointments.model.County
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Service
import com.example.healthhub.data.appointments.model.Specialization
import com.example.healthhub.data.info.model.Location
import com.example.healthhub.network.resource.Resource
import com.example.healthhub.network.resource.Status

class AppointmentsMockedRepositoryImpl : AppointmentsRepository {
    override suspend fun getAppointments(
        usersInfo: UsersInfo,
        timeframe: AppointmentTimeframe
    ): Resource<List<Appointment>> {
        return Resource(
            status = Status.Success,
            payload = getMockedAppointments()
        )
    }

    override suspend fun getSpecializations(): Resource<List<Specialization>> {
        return Resource(
            status = Status.Success,
            payload = getMockedSpecializations()
        )
    }

    override suspend fun getMedics(): Resource<List<Medic>> {
        return Resource(
            status = Status.Success,
            payload = getMockedMedics()
        )
    }

    override suspend fun getCounties(): Resource<List<County>> {
        return Resource(
            status = Status.Success,
            payload = getMockedCounties()
        )
    }

    private fun getMockedSpecializations() = List(5) { specializationIndex ->
        Specialization(
            id = specializationIndex,
            name = "Specialization $specializationIndex",
            description = "$specializationIndex",
            services = getMockedServices()
        )
    }

    private fun getMockedServices() = List(5) { serviceIndex ->
        Service(
            id = serviceIndex,
            name = "Service $serviceIndex",
            description = "$serviceIndex",
            price = serviceIndex.toFloat(),
            duration = serviceIndex
        )
    }

    private fun getMockedCounties() = List(5) { countyIndex ->
        County(
            id = countyIndex,
            name = "County $countyIndex",
        )
    }

    private fun getMockedLocations() = List(5) { locationIndex ->
        Location(
            id = "$locationIndex",
            name = "Location $locationIndex",
            address = "Address $locationIndex",
            latitude = locationIndex.toDouble(),
            longitude = locationIndex.toDouble()
        )
    }

    private fun getMockedMedics() = List(5) { medicIndex ->
        Medic(
            id = medicIndex,
            name = "Medic $medicIndex",
            ranking = medicIndex.toFloat(),
            specializations = getMockedSpecializations(),
            services = getMockedServices(),
            locations = getMockedLocations(),
            county = County(
                id = medicIndex,
                name = "County $medicIndex"
            )
        )
    }

    private fun getMockedAppointments() = List(5) { appointmentIndex ->
        Appointment(
            id = appointmentIndex,
            availableAppointmentId = appointmentIndex,
            userId = appointmentIndex,
            medic = Medic(
                id = appointmentIndex,
                name = "Medic $appointmentIndex",
                ranking = appointmentIndex.toFloat(),
                specializations = getMockedSpecializations(),
                services = getMockedServices(),
                locations = getMockedLocations(),
                county = County(
                    id = appointmentIndex,
                    name = "County $appointmentIndex"
                )
            ),
            county = County(
                id = appointmentIndex,
                name = "County $appointmentIndex"
            ),
            location = Location(
                id = "$appointmentIndex",
                name = "Location $appointmentIndex",
                address = "Address $appointmentIndex",
                latitude = appointmentIndex.toDouble(),
                longitude = appointmentIndex.toDouble()
            ),
            state = "State $appointmentIndex",
            startDate = "Start date $appointmentIndex",
            duration = appointmentIndex,
            price = appointmentIndex.toFloat(),
            specialization = Specialization(
                id = appointmentIndex,
                name = "Specialization $appointmentIndex",
                description = "$appointmentIndex",
                services = getMockedServices()
            ),
            childId = null,
            specializations = getMockedSpecializations()
        )
    }
}
