package com.hacks.radish.dagger.modules

import com.hacks.radish.managers.NetworkManager
import com.hacks.radish.repo.api.feed.IFeedApi
import dagger.Module
import dagger.Provides

@Module(includes = [MainApplicationModule::class])
class NetworkModule {

    @Provides
    fun feedApi(networkManager: NetworkManager) : IFeedApi {
        return networkManager.retrofit.create(IFeedApi::class.java)
    }
}