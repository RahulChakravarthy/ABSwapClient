package com.hacks.radish.dagger.modules

import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class FragmentModule @Inject constructor(val supportFragmentManager: FragmentManager) {

    @Provides
    fun fragmentManager() : FragmentManager {
        return supportFragmentManager
    }
}