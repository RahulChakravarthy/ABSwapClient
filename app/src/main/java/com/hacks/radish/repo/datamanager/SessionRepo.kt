package com.hacks.radish.repo.datamanager

import android.se.omapi.Session
import com.hacks.radish.repo.api.BaseRepo
import com.hacks.radish.repo.api.session.ISessionApi
import com.hacks.radish.repo.dataobject.SessionDO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRepo @Inject constructor(val sessionApi : ISessionApi) : BaseRepo() {

    suspend fun newSession() : SessionDO {
        return with(sessionApi.newSession()) {
            if (isSuccessful) {
                withContext(Dispatchers.IO) {
                }
                body()!!
            } else {
                throw Exception("Error fetching Session from Backend")
            }
        }
    }

    fun getSession() : SessionDO {
        return SessionDO("Test")
    }

    suspend fun updateSession() : SessionDO {
        return with(sessionApi.updateSession(getSession())) {
            if (isSuccessful) {
                withContext(Dispatchers.IO) {

                }
                body()!!
            } else {
                throw Exception("Error fetching Session from Backend")
            }
        }
    }


}