package tech.r7chakra.abswapclient.dagger.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import tech.r7chakra.abswapclient.viewmodels.MainViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}