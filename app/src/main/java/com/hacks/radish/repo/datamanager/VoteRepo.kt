package com.hacks.radish.repo.datamanager

import com.hacks.radish.repo.api.BaseRepo
import com.hacks.radish.repo.api.vote.IVoteApi
import com.hacks.radish.repo.dataobject.VoteDO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoteRepo @Inject constructor(private val voteApi : IVoteApi) : BaseRepo() {

    enum class Status {
        VOTE_SUCCEED, VOTE_FAILED
    }

    suspend fun voteImagePair(voteDO : VoteDO) : Status {
        val response = voteApi.voteImagePair(voteDO)
        return  if (response.isSuccessful)
            Status.VOTE_SUCCEED
            else
            Status.VOTE_FAILED
    }
}