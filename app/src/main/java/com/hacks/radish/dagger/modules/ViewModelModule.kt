package com.hacks.radish.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.hacks.radish.dagger.annotations.ViewModelKey
import com.hacks.radish.viewmodels.MainActivityViewModel
import com.hacks.radish.viewmodels.MainViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun mainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}