package com.hacks.radish.repo.api.session

import com.hacks.radish.repo.dataobject.SessionDO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ISessionApi {

    @POST("/session")
    fun newSession() : Response<SessionDO>

    @POST("/session/update")
    fun updateSession(@Body sessionDO: SessionDO) : Response<SessionDO>
}