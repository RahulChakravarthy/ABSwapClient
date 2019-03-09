package tech.r7chakra.abswapclient.managers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton


@Singleton
class RetrofitManager {



    private fun buildRetrofitClient(baseUrl: String, factory: Factory): Retrofit {
        //For debugging
        Timber.plant(Timber.DebugTree())
        //Http Logging interceptor
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.i(message) }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(factory)
        return builder.build()
    }
}