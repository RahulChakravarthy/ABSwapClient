package com.hacks.radish.dagger.components

import androidx.fragment.app.FragmentManager
import dagger.Component
import com.hacks.radish.dagger.modules.FragmentModule
import javax.inject.Singleton


@Singleton
@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun fragmentManager() : FragmentManager

    // fun inject(mainActivity : MainActivity)
}