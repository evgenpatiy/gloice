package com.gmail.evgenpatiy.gloice

import com.gmail.evgenpatiy.gloice.di.components.AppComponent
import com.gmail.evgenpatiy.gloice.di.components.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GloiceApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent: AppComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}