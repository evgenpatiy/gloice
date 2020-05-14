package com.gmail.evgenpatiy.gloice.di.modules.ui.activities

import android.app.Activity
import android.content.Context
import com.gmail.evgenpatiy.gloice.di.qualifiers.ActivityContext
import com.gmail.evgenpatiy.gloice.di.scopes.ActivityScope

import dagger.Module

import dagger.Provides

@Module
object MainActivityModule {

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(activity: Activity): Context = activity
}