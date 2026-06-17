package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import timber.log.Timber
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        // 1. Find the NavController from your NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // 2. Set the toolbar as the Action Bar
        setSupportActionBar(binding.toolbar)

        // 3. Define top-level destinations (screens where the "Up" arrow should NOT show)
        // Usually these are the Login, Welcome, and Instruction screens.
        appBarConfiguration = androidx.navigation.ui.AppBarConfiguration(
            kotlin.collections.setOf(R.id.loginFragment, R.id.welcomeFragment, R.id.shoeListFragment)
        )

        // 4. Link the NavController to the Toolbar/ActionBar
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
