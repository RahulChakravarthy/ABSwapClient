package tech.r7chakra.abswapclient.dagger.components

import android.app.Application
import android.content.Context
import dagger.Component
import tech.r7chakra.abswapclient.dagger.modules.MainApplicationModule
import tech.r7chakra.abswapclient.dagger.modules.ManagerModule
import tech.r7chakra.abswapclient.dagger.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [MainApplicationModule::class, ManagerModule::class, ViewModelModule::class])
interface MainApplicationComponent {

    fun context() : Context

    fun application() : Application

}