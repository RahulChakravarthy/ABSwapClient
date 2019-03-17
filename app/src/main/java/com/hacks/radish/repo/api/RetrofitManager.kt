package com.hacks.radish.repo.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.hacks.radish.R
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitManager @Inject constructor(context : Context) {

    val retrofit : Retrofit
    private val logging : HttpLoggingInterceptor
    private val client : OkHttpClient
    private val gson : Gson
    private val converterFactory : GsonConverterFactory

    init {
        //For debugging
        Timber.plant(Timber.DebugTree())
        //Http Logging interceptor
        logging = HttpLoggingInterceptor { message -> Timber.i(message) }
        client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        gson = GsonBuilder().create()
        converterFactory = GsonConverterFactory.create(gson)

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(context.getString(R.string.host_name))
            .addConverterFactory(converterFactory)
            .build()
    }
}