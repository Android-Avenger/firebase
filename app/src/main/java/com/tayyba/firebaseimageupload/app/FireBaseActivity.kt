package com.tayyba.firebaseimageupload.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.tayyba.firebaseimageupload.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FireBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base_app)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.app_navigation_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.navigation)
        navGraph.setStartDestination(R.id.registerFragment)
        navController.graph = navGraph

    }
}