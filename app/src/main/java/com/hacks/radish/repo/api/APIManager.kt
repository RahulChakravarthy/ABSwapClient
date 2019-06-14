package com.hacks.radish.repo.api

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.hacks.radish.repo.dataobject.FeedDO
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class APIManager @Inject constructor(private val networkManager: NetworkManager,
                                     private val context : Context) {

    /**
     * Make rest call to backend to fetch more FeedDOs
     */
    fun getFeed(size : Int, onResponse : (response : List<FeedDO>?) -> Unit) {
        networkManager.retrofit.create(IApiManager::class.java).getFeed(size).enqueue(object : Callback<List<FeedDO>> {
            override fun onResponse(call: Call<List<FeedDO>>, response: Response<List<FeedDO>>) {
                if (response.isSuccessful) {
                    onResponse(response.body())
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<FeedDO>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}