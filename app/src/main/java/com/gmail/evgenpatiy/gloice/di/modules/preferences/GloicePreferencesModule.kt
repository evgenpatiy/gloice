package com.gmail.evgenpatiy.gloice.di.modules.preferences

import android.content.Context
import com.gmail.evgenpatiy.gloice.di.qualifiers.ApplicationContext
import com.gmail.evgenpatiy.gloice.di.scopes.ApplicationScope
import com.gmail.evgenpatiy.gloice.preferences.GloicePreferences
import dagger.Module
import dagger.Provides

@Module
object GloicePreferencesModule {

    @Provides
    @ApplicationScope
    fun provideGloicePreferences(@ApplicationContext context: Context) = GloicePreferences(context)
}