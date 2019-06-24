package com.hacks.radish.managers

import android.content.Context
import android.content.ContextWrapper
import com.hacks.radish.keys.SESSION_ID
import com.hacks.radish.util.EMPTY
import com.pixplicity.easyprefs.library.Prefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(val context: Context) {
    companion object {
        const val SHARED_PREFS_NAME = "ABSwapClient_Default"
    }
    init {
        Prefs.Builder()
            .setContext(context)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(SHARED_PREFS_NAME)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    fun putString(key : String, value : String) {
        Prefs.putString(key, value)
    }

    fun getString(key : String, default : String) : String {
        return Prefs.getString(key, default)
    }

    fun putInt(key : String, value : Int) {
        Prefs.putInt(key, value)
    }

    fun getInt(key : String, default : Int) : Int {
        return Prefs.getInt(key, default)
    }

    fun putBool(key : String, value : Boolean) {
        Prefs.putBoolean(key, value)
    }

    fun getBool(key : String, default : Boolean) : Boolean {
        return  Prefs.getBoolean(key, default)
    }

    fun putFloat(key : String, value : Float) {
        Prefs.putFloat(key, value)
    }

    fun getFloat(key : String, default : Float) : Float {
        return Prefs.getFloat(key, default)
    }

    fun putLong(key : String, value : Long) {
        Prefs.putLong(key, value)
    }

    fun getLong(key : String, default : Long) : Long {
        return Prefs.getLong(key, default)
    }

    fun putDouble(key : String, value : Double) {
        Prefs.putDouble(key, value)
    }

    fun getDouble(key : String, default : Double) : Double {
        return Prefs.getDouble(key, default)
    }

    fun putSessionId(session : String) {
        Prefs.putString(SESSION_ID, session)
    }

    fun getSessionId() : String {
        return Prefs.getString(SESSION_ID, String.EMPTY)
    }
}
