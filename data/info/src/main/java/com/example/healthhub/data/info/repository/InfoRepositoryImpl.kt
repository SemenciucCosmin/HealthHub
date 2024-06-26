package com.example.healthhub.data.info.repository

import com.example.healthhub.data.info.model.Location
import com.example.healthhub.network.api.service.LocationsApi
import com.example.healthhub.network.resource.Resource

class InfoRepositoryImpl(private val locationsApi: LocationsApi) : InfoRepository {
    override suspend fun getLocations(): Resource<List<Location>> {
        val resource = locationsApi.getLocations()
        val locations = resource.payload?.innerLocationsDTO?.locationEntities?.mapNotNull {
            Location(
                id = it.id ?: return@mapNotNull null,
                name = it.name ?: return@mapNotNull null,
                address = it.address ?: return@mapNotNull null,
                latitude = it.latitude ?: return@mapNotNull null,
                longitude = it.longitude ?: return@mapNotNull null
            )
        } ?: emptyList()

        return Resource(locations, resource.status)
    }
}
