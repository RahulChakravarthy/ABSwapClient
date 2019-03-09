package tech.r7chakra.abswapclient.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class MainApplicationModule(val application: Application) {

    @Provides
    fun application() : Application {
        return this.application
    }

    @Provides
    fun context() : Context {
        return this.application
    }
}