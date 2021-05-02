package com.gitalive.isat

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gitalive.isat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_about, R.id.nav_web
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        permissionAccess()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun permissionAccess() {
        val pmsInt =
            ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.INTERNET)
        val pmsANS = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.ACCESS_NETWORK_STATE
        )
        val pmsAWS = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.ACCESS_WIFI_STATE
        )
        val pmsWES = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (pmsInt != PackageManager.PERMISSION_GRANTED ||
            pmsANS != PackageManager.PERMISSION_GRANTED ||
            pmsAWS != PackageManager.PERMISSION_GRANTED ||
            pmsWES != PackageManager.PERMISSION_GRANTED
        ) {
            val access = arrayOf(
                Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(this, access, 0) //若没有权限则申请授权
        }
    }

}
