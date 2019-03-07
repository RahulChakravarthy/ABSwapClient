package tech.r7chakra.abswapclient.activities

import android.R
import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainApplication : Application() {

    companion object {
        lateinit var instance : MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}