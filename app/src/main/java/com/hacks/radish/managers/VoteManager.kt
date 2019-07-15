package com.hacks.radish.managers

import androidx.lifecycle.MutableLiveData
import com.hacks.radish.repo.api.vote.VoteRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoteManager @Inject constructor() {

    val voteLiveData by lazy {
        MutableLiveData<VoteRepo.Status>()
    }
}