package com.example.healthhub.network.call

import com.example.healthhub.network.resource.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.net.ssl.HttpsURLConnection

class Call<T>(
    private val call: Call<T>
) : CallDelegate<T, Resource<T>>(call) {
    override fun enqueueImpl(callback: Callback<Resource<T>>) {
        call.enqueue(callback(callback))
    }

    override fun cloneImpl(): Call<Resource<T>> = Call(call.clone())

    private fun callback(callback: Callback<Resource<T>>) = object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val body = response.body()
            val code = response.code()
            val resource: Resource<T> = when {
                response.isSuccessful && body != null -> Resource.Success<T>(body)
                response.isSuccessful && body == null -> Resource.Error.NotFound()
                code == HttpsURLConnection.HTTP_UNAUTHORIZED -> Resource.Error.Authorization()
                code == HttpsURLConnection.HTTP_INTERNAL_ERROR -> Resource.Error.Server()
                code == HttpsURLConnection.HTTP_NOT_FOUND -> Resource.Error.NotFound()
                else -> Resource.Error.Access()
            }

            val success = Response.success(resource)
            callback.onResponse(this@Call, success)
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            val resource: Resource<T> = when (throwable) {
                is IOException -> Resource.Error.Network()
                else -> Resource.Error.Access()
            }
            val success = Response.success(resource)
            callback.onResponse(this@Call, success)
        }
    }
}
