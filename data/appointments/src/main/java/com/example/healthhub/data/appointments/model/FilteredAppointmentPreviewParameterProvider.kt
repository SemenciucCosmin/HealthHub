package com.example.healthhub.data.appointments.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.healthhub.data.info.model.Location

class FilteredAppointmentPreviewParameterProvider : PreviewParameterProvider<FilteredAppointment> {
    override val values = sequenceOf(
        FilteredAppointment(
            id = 9963,
            doctorFullName = "Liza Clements",
            doctorId = 4258,
            ranking = 9364,
            specializationId = 9759,
            specializationName = "Tia Webb",
            services = List(3) {
                Service(
                    id = 8510,
                    name = "Alyssa Wise",
                    description = "comprehensam",
                    price = 18.19f,
                    duration = 9288
                )
            },
            location = Location(
                id = 123,
                name = "Mari Gillespie",
                address = "tale",
                latitude = 12.13,
                longitude = 14.15
            ),
            county = County(
                id = 4879,
                name = "August Williams"
            ),
            dateMillis = 5278
        )
    )
}
