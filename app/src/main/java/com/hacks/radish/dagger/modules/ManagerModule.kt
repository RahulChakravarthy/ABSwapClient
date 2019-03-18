package com.hacks.radish.dagger.modules

import android.content.Context
import com.hacks.radish.managers.SharedPreferencesManager
import dagger.Module
import dagger.Provides

@Module(includes = [MainApplicationModule::class])
class ManagerModule (val context: Context) {
    @Provides
    fun provideSharedPreferencesManager(): SharedPreferencesManager{
        return SharedPreferencesManager(context)
    }
}