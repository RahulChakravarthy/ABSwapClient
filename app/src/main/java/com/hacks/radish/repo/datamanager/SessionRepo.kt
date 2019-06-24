package com.hacks.radish.repo.datamanager

import com.hacks.radish.managers.SharedPreferencesManager as SPM
import com.hacks.radish.repo.api.BaseRepo
import com.hacks.radish.repo.api.session.ISessionApi
import com.hacks.radish.repo.dataobject.SessionDO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRepo @Inject constructor(val sessionApi : ISessionApi,
                                      /* Use Shared Prefs for now */ val spm : SPM) : BaseRepo() {

    suspend fun newSession() : SessionDO {
        return with(sessionApi.newSession()) {
            if (isSuccessful) {
                withContext(Dispatchers.IO) {
                    spm.putSessionId(body()!!.sessionId)
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
                    spm.putSessionId(body()!!.sessionId)
                }
                body()!!
            } else {
                throw Exception("Error fetching Session from Backend")
            }
        }
    }


}