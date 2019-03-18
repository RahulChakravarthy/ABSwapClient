package com.hacks.radish.managers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(val context: Context) {
    companion object {
        const val SHARED_PREFS_NAME = "ABSwapClient_Default"
    }
    val sharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE)
    }
}