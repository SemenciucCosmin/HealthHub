package com.example.healthhub.data.info.repository

import com.example.healthhub.data.info.model.Location
import com.example.healthhub.network.resource.Resource

interface InfoRepository {
    suspend fun getLocations(): Resource<List<Location>>
}
