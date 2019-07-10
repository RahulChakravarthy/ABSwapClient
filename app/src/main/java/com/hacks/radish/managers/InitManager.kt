package com.hacks.radish.managers

import com.hacks.radish.repo.api.session.SessionRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InitManager @Inject constructor(private val sessionRepo: SessionRepo) {

    fun init() {
        //Fetch new session
        //For now use global scope
        GlobalScope.launch {
            sessionRepo.newSession()
        }
    }

}