package com.hacks.radish.repo.api

import com.hacks.radish.repo.dataobject.ImagePairDO
import com.hacks.radish.repo.dataobject.ImagePairsDO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiManager {

    @GET("/feed")
    fun getFeed(@Query("size") size : Int) : Call<ImagePairsDO>
}