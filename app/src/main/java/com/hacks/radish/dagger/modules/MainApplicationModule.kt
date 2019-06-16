package com.hacks.radish.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class MainApplicationModule(val application: Application) {

    @Provides
    fun application() : Application {
        return application
    }

    @Provides
    fun context() : Context {
        return application
    }
}