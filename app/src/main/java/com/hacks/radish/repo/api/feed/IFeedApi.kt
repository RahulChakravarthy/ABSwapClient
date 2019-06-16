package com.hacks.radish.repo.api.feed

import com.hacks.radish.repo.dataobject.ImagePairsDO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IFeedApi {

    @GET("/feed")
    suspend fun getFeed(@Query("size") size : Int) : Response<ImagePairsDO>
}