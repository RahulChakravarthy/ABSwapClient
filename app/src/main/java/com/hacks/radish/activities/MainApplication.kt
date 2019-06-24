package com.hacks.radish.activities

import android.app.Application
import com.hacks.radish.dagger.components.DaggerMainApplicationComponent
import com.hacks.radish.dagger.components.MainApplicationComponent
import com.hacks.radish.dagger.modules.MainApplicationModule
import com.hacks.radish.managers.InitManager
import javax.inject.Inject

class MainApplication : Application() {

    companion object {
        lateinit var instance : MainApplication
        lateinit var mainApplicationComponent : MainApplicationComponent

    }

    @Inject
    lateinit var initManager: InitManager

    override fun onCreate() {
        super.onCreate()
        instance = this
        mainApplicationComponent =
            DaggerMainApplicationComponent
                .builder()
                .mainApplicationModule(MainApplicationModule(this))
                .build()

        mainApplicationComponent.inject(this)
        initManager.init()
    }
}