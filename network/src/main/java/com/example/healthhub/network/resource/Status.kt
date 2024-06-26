package com.example.healthhub.network.resource

sealed class Status {
    data object Loading : Status()

    data object Success : Status()

    data object Empty : Status()

    data object ResourceNotFoundError : Status()

    data object ResourceAccessError : Status()

    data object ResourceNetworkError : Status()

    data object ResourceAuthorizationError : Status()

    data object ResourceServerError : Status()

    data object ResourceGone : Status()
}