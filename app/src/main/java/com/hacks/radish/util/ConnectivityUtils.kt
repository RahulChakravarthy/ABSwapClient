package com.hacks.radish.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.IntDef
import com.hacks.radish.activities.MainApplication

object ConnectivityUtils {
    val TAG = ConnectivityUtils::class.java.simpleName

    const val INTERNET_UNAVAILABLE = 0
    const val INTERNET_WIFI = 1
    const val INTERNET_CELLULAR = 2

    val isNetworkAvailable: Boolean
        get() {
            val cm = MainApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return isNetworkAvailableInternal(cm.activeNetworkInfo)
        }

    val isConnectedToWifi: Boolean
        get() {
            val cm = MainApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return isNetworkAvailableInternal(activeNetwork) && activeNetwork.type == ConnectivityManager.TYPE_WIFI
        }

    val isConnectedToCellular: Boolean
        get() {
            val cm = MainApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return isNetworkAvailableInternal(activeNetwork) && activeNetwork.type == ConnectivityManager.TYPE_MOBILE
        }

    val internetStatus: Int
        @InternetStatus get() {
            val cm = MainApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (!isNetworkAvailableInternal(activeNetwork)) {
                return INTERNET_UNAVAILABLE
            }
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                return INTERNET_WIFI
            }
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                return INTERNET_CELLULAR
            }
            // Crashlytics.logException(Exception(TAG + ": Unknown internet state: " + activeNetwork.type))
            return INTERNET_UNAVAILABLE
        }

    @IntDef(INTERNET_UNAVAILABLE, INTERNET_WIFI, INTERNET_CELLULAR)
    @Retention(AnnotationRetention.SOURCE)
    annotation class InternetStatus

    private fun isNetworkAvailableInternal(info: NetworkInfo?): Boolean {
        return info != null && info.isConnected
    }
}