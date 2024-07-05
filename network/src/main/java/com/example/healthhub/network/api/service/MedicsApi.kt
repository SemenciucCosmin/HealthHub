package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.ChildMedicsDTO
import com.example.healthhub.network.api.model.MedicsDTO
import com.example.healthhub.network.api.model.ParentMedicsDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface MedicsApi {
    @GET("/api/v1/medicalclinicproject/doctor/getAllMedics")
    suspend fun getMedics(): Resource<MedicsDTO>

    @GET("/api/v1/medicalclinicproject/doctor/getMedicsByUserId/{parentId}")
    suspend fun getParentMedics(@Path("parentId") parentId: Int): Resource<ParentMedicsDTO>

    @GET("/api/v1/medicalclinicproject/doctor/getMedicsByChildId/{childId}")
    suspend fun getChildMedics(@Path("childId") childId: Int): Resource<ChildMedicsDTO>
}
