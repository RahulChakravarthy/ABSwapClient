package tech.r7chakra.abswapclient.dagger.components

import android.app.Application
import android.content.Context
import dagger.Component
import tech.r7chakra.abswapclient.activities.MainActivity
import tech.r7chakra.abswapclient.dagger.modules.MainApplicationModule
import tech.r7chakra.abswapclient.dagger.modules.ManagerModule
import tech.r7chakra.abswapclient.dagger.modules.ViewModelModule
import tech.r7chakra.abswapclient.fragments.FeedFragment
import tech.r7chakra.abswapclient.fragments.MainActivityFragment
import tech.r7chakra.abswapclient.fragments.PostFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [MainApplicationModule::class, ManagerModule::class, ViewModelModule::class])
interface MainApplicationComponent {

    fun context() : Context

    fun application() : Application

    fun inject(mainActivity: MainActivity)

    fun inject(mainActivity: MainActivityFragment)

    fun inject(postFragment: PostFragment)

    fun inject(feedFragment: FeedFragment)

}