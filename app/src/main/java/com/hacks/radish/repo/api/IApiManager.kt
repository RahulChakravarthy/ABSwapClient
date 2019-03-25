package com.hacks.radish.repo.api

import com.hacks.radish.repo.dataobject.FeedDO
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface IApiManager {

    @POST("/feed")
    fun getFeed(@Query("size") size : Int) : Call<ArrayList<FeedDO>>
}