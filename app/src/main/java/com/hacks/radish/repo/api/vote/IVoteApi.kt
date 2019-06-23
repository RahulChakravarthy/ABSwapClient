package com.hacks.radish.repo.api.vote

import com.hacks.radish.repo.dataobject.VoteDO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IVoteApi {

    @POST("/image_pair/vote")
    suspend fun voteImagePair(@Body voteDO: VoteDO) : Response<String>
}