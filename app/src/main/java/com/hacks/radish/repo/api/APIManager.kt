package com.hacks.radish.repo.api

import android.content.Context
import android.widget.Toast
import com.hacks.radish.repo.dataobject.ImagePairsDO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIManager @Inject constructor(private val networkManager: NetworkManager,
                                     private val context : Context) {

    /**
     * Make rest call to backend to fetch more FeedDOs
     */
    fun getFeed(size : Int, onResponse : (response : ImagePairsDO?) -> Unit) {
        networkManager.retrofit.create(IApiManager::class.java).getFeed(size).enqueue(object : Callback<ImagePairsDO> {
            override fun onResponse(call: Call<ImagePairsDO>, response: Response<ImagePairsDO>) {
                if (response.isSuccessful) {
                    onResponse(response.body())
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ImagePairsDO>, t: Throwable) {
                Timber.d(t)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}