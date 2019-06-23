package com.hacks.radish.repo.datamanager

import com.hacks.radish.repo.api.feed.IFeedApi
import com.hacks.radish.repo.dataobject.ImagePairsDO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedRepo @Inject constructor(val feedApi: IFeedApi) {

    suspend fun getFeed(size : Int) : ImagePairsDO? {
        val response = feedApi.getFeed(size)
        //For now only handle success and failure response codes
        return if (response.isSuccessful) {
            //Store response in local DB
            withContext(Dispatchers.IO) {
                //logic
            }
            //Return image pairs
            response.body()
        } else {
            //In the future, add logic to handle failed even
            //(Put auth issues in http interceptors since those are more general)
            //Pull from local RoomDB and populate return value with stale response
            withContext(Dispatchers.IO) {
                response.body() //Perform DB access on IO thread
            }
        }
    }
}