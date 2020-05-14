package com.gmail.evgenpatiy.gloice.di.components

import android.app.Application
import com.gmail.evgenpatiy.gloice.GloiceApplication
import com.gmail.evgenpatiy.gloice.di.modules.application.AppModule
import com.gmail.evgenpatiy.gloice.di.modules.database.GloiceDatabaseModule
import com.gmail.evgenpatiy.gloice.di.modules.network.RetrofitModule
import com.gmail.evgenpatiy.gloice.di.modules.preferences.GloicePreferencesModule
import com.gmail.evgenpatiy.gloice.di.modules.ui.ScreenBuilderModule
import com.gmail.evgenpatiy.gloice.di.modules.ui.viewmodels.ViewModelModule
import com.gmail.evgenpatiy.gloice.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@ApplicationScope
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        ScreenBuilderModule::class,
        ViewModelModule::class,
        RetrofitModule::class,
        GloicePreferencesModule::class,
        GloiceDatabaseModule::class]
)

interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: GloiceApplication?)

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}