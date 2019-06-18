package com.hacks.radish.dagger.components

import android.app.Application
import android.content.Context
import com.hacks.radish.activities.MainActivity
import com.hacks.radish.dagger.modules.MainApplicationModule
import com.hacks.radish.dagger.modules.ManagerModule
import com.hacks.radish.dagger.modules.NetworkModule
import com.hacks.radish.dagger.modules.ViewModelModule
import com.hacks.radish.fragments.FeedFragment
import com.hacks.radish.fragments.GalleryFragment
import com.hacks.radish.fragments.PostFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainApplicationModule::class, ManagerModule::class, ViewModelModule::class, NetworkModule::class])
interface MainApplicationComponent {

    fun context() : Context

    fun application() : Application

    fun inject(mainActivity: MainActivity)

    fun inject(postFragment: PostFragment)

    fun inject(feedFragment: FeedFragment)

    fun inject(galleryFragment: GalleryFragment)

}