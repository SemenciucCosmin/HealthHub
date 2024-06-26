package com.example.healthhub.network.call

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CallDelegate<T, U>(private val call: Call<T>) : Call<U> {
    final override fun clone(): Call<U> = cloneImpl()

    override fun execute(): Response<U> = throw NotImplementedError()

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

    override fun enqueue(callback: Callback<U>) = enqueueImpl(callback)

    abstract fun enqueueImpl(callback: Callback<U>)
    abstract fun cloneImpl(): Call<U>
}
