package com.example.healthhub.data.model

sealed class Status {
    data object Loading : Status()
    data object Success : Status()
    data object NetworkError : Status()
    data object DataError : Status()
    data object None : Status()
}
