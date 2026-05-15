package com.jobsearch.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jobsearch.app.R
import com.jobsearch.app.databinding.ActivityMainBinding
import com.jobsearch.app.notification.NotificationHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NotificationHelper.createNotificationChannel(this)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        AppBarConfiguration(
            setOf(R.id.navigation_search, R.id.navigation_saved, R.id.navigation_alerts)
        )

        binding.bottomNavigation.setupWithNavController(navController)

        // Deep-link from notification tap
        intent.getStringExtra(EXTRA_SEARCH_KEYWORD)?.let { keyword ->
            navController.navigate(R.id.navigation_search)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_SEARCH_KEYWORD = "extra_search_keyword"
    }
}
