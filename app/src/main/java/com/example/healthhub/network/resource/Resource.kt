package com.example.healthhub.network.resource


sealed class Resource<out T> {
    val isSuccessful: Boolean
        get() = this is Success


    fun getOrNull(): T? {
        return when (this) {
            is Success -> data
            is Error -> null
        }
    }

    fun errorOrNull(): Error<T>? {
        return when (this) {
            is Error -> this
            is Success -> null
        }
    }

    data class Success<out T>(val data: T) : Resource<T>()
    sealed class Error<out T> : Resource<T>() {
        class Access<out T> : Error<T>()

        class Authorization<out T> : Error<T>()

        class Network<out T> : Error<T>()

        class NotFound<out T> : Error<T>()

        class Server<out T> : Error<T>()

        fun <U> getErrorType(): Error<U> {
            return when (this) {
                is Access -> Access()
                is Authorization -> Authorization()
                is Network -> Network()
                is NotFound -> NotFound()
                is Server -> Server()
            }
        }
    }
}
