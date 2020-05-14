package com.gmail.evgenpatiy.gloice.di.modules.application

import android.app.Application
import android.content.Context
import com.gmail.evgenpatiy.gloice.di.qualifiers.ApplicationContext
import com.gmail.evgenpatiy.gloice.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext(application: Application): Context = application
}