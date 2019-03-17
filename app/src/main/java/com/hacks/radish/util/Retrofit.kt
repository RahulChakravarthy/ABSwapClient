package com.hacks.radish.util

import retrofit2.Call
import java.io.IOException

@Throws(IOException::class)
fun <T> Call<T>.getSynchronousOrThrow(): T {
    val response = execute()
    if (response?.body() == null) {
        throw IOException("Response was null")
    }

    return response.body()!!
}