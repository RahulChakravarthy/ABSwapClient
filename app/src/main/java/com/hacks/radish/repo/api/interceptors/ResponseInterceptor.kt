package com.hacks.radish.repo.api.interceptors

import com.hacks.radish.repo.dataobject.DataObject
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
        ).apply {
            body()
            when(code()){
                200 -> storeResponseData(body())
                403 -> reAuthentication()
                else ->  {/* Do nothing */}
            }
        }
    }

    /**
     * Redirect user back to login screen for re-Authentication
     */
    private fun reAuthentication() {

    }

    /**
     * Store response Data Object in Database
     */
    private fun storeResponseData(responseBody : ResponseBody?) {
        if (responseBody is DataObject) {
            //Store in database
        }
    }

}