package com.example.healthhub.network.call

import com.example.healthhub.network.resource.Resource
import com.example.healthhub.network.resource.Status
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
                response.isSuccessful && body != null -> {
                    Resource(body, Status.Success)
                }

                response.isSuccessful && body == null -> {
                    Resource(null, Status.Empty)
                }

                code in listOf(
                    HttpsURLConnection.HTTP_FORBIDDEN,
                    HttpsURLConnection.HTTP_UNAUTHORIZED,
                ) -> Resource(null, Status.ResourceAuthorizationError)

                code == HttpsURLConnection.HTTP_INTERNAL_ERROR -> {
                    Resource(null, Status.ResourceServerError)
                }

                code == HttpsURLConnection.HTTP_NOT_FOUND -> {
                    Resource(null, Status.ResourceNotFoundError)
                }

                code == HttpsURLConnection.HTTP_GONE -> {
                    Resource(null, Status.ResourceGone)
                }

                else -> Resource(null, Status.ResourceAccessError)
            }

            val success = Response.success(resource)
            callback.onResponse(this@Call, success)
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            val resource: Resource<T> = when (throwable) {
                is IOException -> Resource(null, Status.ResourceNetworkError)
                else -> Resource(null, Status.ResourceAccessError)
            }
            val success = Response.success(resource)
            callback.onResponse(this@Call, success)
        }
    }
}
