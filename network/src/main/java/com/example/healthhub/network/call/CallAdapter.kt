package com.example.healthhub.network.call

import com.example.healthhub.network.resource.Resource
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class CallAdapter(
    private val type: Type
) : CallAdapter<Type, Call<Resource<Type>>> {
    override fun responseType(): Type = type

    override fun adapt(call: Call<Type>): Call<Resource<Type>> = Call(call)
}
