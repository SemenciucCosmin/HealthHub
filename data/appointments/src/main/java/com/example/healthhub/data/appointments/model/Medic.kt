package com.example.healthhub.data.appointments.model

import com.example.healthhub.data.info.model.Location

data class Medic(
    val id: Int,
    val name: String,
    val ranking: Float,
    val specializations: List<Specialization>,
    val services: List<Service>,
    val locations: List<Location>,
    val county: County,
) {
    companion object {
        const val INVALID_ID = -1
    }
}
