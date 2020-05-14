package com.gmail.evgenpatiy.gloice.ui.fragments

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AbstractFragment : DaggerFragment() {

    /**
     *  will be used as factory in children fragments
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}