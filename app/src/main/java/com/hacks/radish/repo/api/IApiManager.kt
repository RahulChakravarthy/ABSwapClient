package com.hacks.radish.repo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.hacks.radish.repo.dataobject.FeedDO

interface IApiManager {

    @GET("/feed")
    fun getFeed(@Query("size") size : Int) : Call<List<FeedDO>>
}