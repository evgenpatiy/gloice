package com.gmail.evgenpatiy.gloice.di.modules.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.evgenpatiy.gloice.di.modules.ui.viewmodels.annotations.ViewModelKey
import com.gmail.evgenpatiy.gloice.di.modules.ui.viewmodels.factory.ViewModelFactory
import com.gmail.evgenpatiy.gloice.ui.viewmodels.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    //region FACTORY

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
    //endregion

    //region VIEWMODELS

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel
    //endregions
}