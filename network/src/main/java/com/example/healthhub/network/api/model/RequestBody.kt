package com.example.healthhub.network.api.model

interface RequestBody {
    fun build(): Map<String, Any>
}
