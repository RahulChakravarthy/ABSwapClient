package tech.r7chakra.abswapclient.repo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tech.r7chakra.abswapclient.repo.dataobject.FeedDO

interface IApiManager {

    @GET("/feed")
    fun getFeed(@Query("size") size : Int) : Call<List<FeedDO>>
}