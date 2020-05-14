package com.gmail.evgenpatiy.gloice.di.modules.ui

import com.gmail.evgenpatiy.gloice.di.scopes.ActivityScope
import com.gmail.evgenpatiy.gloice.di.scopes.FragmentScope
import com.gmail.evgenpatiy.gloice.ui.activities.main.MainActivity
import com.gmail.evgenpatiy.gloice.ui.fragments.details.MovieDetailsFragment
import com.gmail.evgenpatiy.gloice.ui.fragments.details.NoPreviousMovieSelectedFragment
import com.gmail.evgenpatiy.gloice.ui.fragments.list.MoviesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreenBuilderModule {

    //region ACTIVITIES
    @ContributesAndroidInjector
    @ActivityScope
    internal abstract fun bindMainActivity(): MainActivity
    //endregion

    //region FRAGMENTS
    @ContributesAndroidInjector
    @FragmentScope
    internal abstract fun bindMoviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector
    @FragmentScope
    internal abstract fun bindMovieDetailsFragment(): MovieDetailsFragment

    @ContributesAndroidInjector
    @FragmentScope
    internal abstract fun bindNoPreviousMovieSelectedFragment(): NoPreviousMovieSelectedFragment
    //endregion
}