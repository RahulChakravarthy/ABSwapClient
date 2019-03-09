package tech.r7chakra.abswapclient.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.r7chakra.abswapclient.dagger.annotations.ViewModelKey
import tech.r7chakra.abswapclient.viewmodels.MainActivityViewModel
import tech.r7chakra.abswapclient.viewmodels.MainViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun mainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}