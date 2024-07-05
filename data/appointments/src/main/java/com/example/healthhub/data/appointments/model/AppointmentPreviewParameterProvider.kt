package com.example.healthhub.data.appointments.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.healthhub.data.info.model.Location

class AppointmentPreviewParameterProvider : PreviewParameterProvider<Appointment> {
    override val values = sequenceOf(
        Appointment(
            id = 3709,
            availableAppointmentId = 6139,
            userId = 3787,
            medic = Medic(
                id = 6527,
                name = "Angeline Travis",
                ranking = 20.21f,
                specializations = listOf(),
                services = listOf(),
                locations = listOf(),
                county = County(
                    id = 1331,
                    name = "Josefina Bell"
                )
            ),
            county = County(
                id = 1331,
                name = "Josefina Bell"
            ),
            location = Location(
                id = 1331,
                name = "Robbie Abbott",
                address = "sanctus",
                latitude = 30.31,
                longitude = 32.33
            ),
            state = "Iowa",
            startDate = "10-06-2024",
            duration = List(60) { it }.random(),
            price = List(30) { it.toFloat() }.random(),
            specialization = Specialization(
                id = 8120,
                name = "Benjamin Franco",
                description = "commodo",
                services = listOf()
            ),
            childId = null,
            specializations = listOf()
        )
    )
}
