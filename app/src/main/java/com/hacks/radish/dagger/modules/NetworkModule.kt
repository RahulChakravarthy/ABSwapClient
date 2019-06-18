package com.hacks.radish.dagger.modules

import com.hacks.radish.managers.NetworkManager
import com.hacks.radish.repo.api.feed.IFeedApi
import com.hacks.radish.repo.api.session.ISessionApi
import com.hacks.radish.repo.api.vote.IVoteApi
import dagger.Module
import dagger.Provides

@Module(includes = [MainApplicationModule::class])
class NetworkModule {

    @Provides fun feedApi(networkManager: NetworkManager) : IFeedApi {
        return networkManager.retrofit.create(IFeedApi::class.java)
    }

    @Provides fun sessionApi(networkManager: NetworkManager) : ISessionApi {
        return networkManager.retrofit.create(ISessionApi::class.java)
    }

    @Provides fun voteApi(networkManager: NetworkManager) : IVoteApi {
        return networkManager.retrofit.create(IVoteApi::class.java)
    }
}