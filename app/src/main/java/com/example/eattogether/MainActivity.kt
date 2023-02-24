package com.example.eattogether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import androidx.navigation.ui.*
import com.example.eattogether.databinding.ActivityMainBinding
import timber.log.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        binding = setContentView(this, R.layout.activity_main)
        binding.apply {
            setSupportActionBar(toolbarMain)
            navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
                .navController
            NavigationUI.setupActionBarWithNavController(this@MainActivity, navController)
            appBarConfiguration = AppBarConfiguration(navController.graph)

            navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
                when (nd.id) {
                    R.id.loginFragment -> {
                        supportActionBar?.setDisplayShowTitleEnabled(true)
                    }
                    R.id.signupFragment -> {
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    }
                    R.id.mapFragment -> {
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    }
                    else -> toolbarTitle.text = ""
                }
                supportActionBar?.setDisplayShowTitleEnabled(true)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}