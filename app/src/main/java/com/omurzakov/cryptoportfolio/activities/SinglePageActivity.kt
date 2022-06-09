package com.omurzakov.cryptoportfolio.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.omurzakov.cryptoportfolio.R
import com.omurzakov.cryptoportfolio.databinding.ActivitySinglePageBinding

class SinglePageActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySinglePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySinglePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_single_page)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.PriceListFragment,
                R.id.HistoryListFragment,
                R.id.PortfolioValueFragment,
                R.id.AnalyticsFragment
            )
        )

        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_single_page)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}