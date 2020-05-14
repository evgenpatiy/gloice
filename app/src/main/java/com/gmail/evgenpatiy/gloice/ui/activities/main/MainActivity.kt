package com.gmail.evgenpatiy.gloice.ui.activities.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.gmail.evgenpatiy.gloice.R
import com.gmail.evgenpatiy.gloice.databinding.MainActivityBinding
import com.gmail.evgenpatiy.gloice.ui.activities.AbstractActivity
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AbstractActivity() {

    private val activityBinding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    private val onDestinationChangedListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.layout.fragment_movie_list -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                else -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }
        }

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment).apply {
            addOnDestinationChangedListener(onDestinationChangedListener)
        }
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }

    private val toolbar: MaterialToolbar by lazy {
        activityBinding.mainToolbar.apply {
            setupWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityBinding.root)
        setSupportActionBar(toolbar)
    }
}
