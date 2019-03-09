package tech.r7chakra.abswapclient.dagger.components

import androidx.fragment.app.FragmentManager
import dagger.Component
import tech.r7chakra.abswapclient.dagger.modules.FragmentModule
import javax.inject.Singleton


@Singleton
@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun fragmentManager() : FragmentManager

    // fun inject(mainActivity : MainActivity)
}