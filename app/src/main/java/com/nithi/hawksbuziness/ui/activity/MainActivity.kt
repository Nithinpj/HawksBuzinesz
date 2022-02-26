package com.nithi.hawksbuziness.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nithi.hawksbuziness.R
import com.nithi.hawksbuziness.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private var backPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        navController=findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
      if (navController.graph.startDestinationId!=navController.currentDestination?.id){
          navController.navigate(R.id.homeFragment)
      }else{
          finish()
      }

    }




}