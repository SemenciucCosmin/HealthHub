package com.example.healthhub.network.resource

data class Resource<T>(val payload: T?, val status: Status)
