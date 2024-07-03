package com.example.healthhub.network.api.model

data class AppointmentRequestBody(
    private val id: Int,
    private val userId: Int,
    private val doctorId: Int,
    private val countyId: Int,
    private val locationId: String,
    private val startDateMillis: Long,
    private val duration: Int,
    private val price: Float,
    private val specializationId: Int,
    private val specializationName: String,
    private val specializationDescription: String,
    private val servicesRequestBody: List<ServiceRequestBody>
) : RequestBody {
    override fun build() = mapOf(
        "availableAppointmentId" to id,
        "userId" to userId,
        "doctorId" to doctorId,
        "countyId" to countyId,
        "locationId" to locationId,
        "state" to "null",
        "appointmentStartDate" to startDateMillis,
        "appointmentDuration" to duration,
        "price" to price,
        "specializationId" to specializationId,
        "specializations" to mapOf(
            "specializationId" to specializationId,
            "specializationName" to specializationName,
            "specializationDescription" to specializationDescription,
            "services" to servicesRequestBody,
        ),
    )
}
