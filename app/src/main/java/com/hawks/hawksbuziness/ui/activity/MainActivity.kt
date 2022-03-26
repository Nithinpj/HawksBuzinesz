package com.hawks.hawksbuziness.ui.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import java.util.concurrent.TimeUnit
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.*
import com.hawks.hawksbuziness.databinding.ActivityMainBinding
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.home.HomeViemodel
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.isLocationPermissionGranted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.utill.onBackPressedListener


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var preferenceManger: PreferenceManger
    private var backPressedOnce = false

    // FusedLocationProviderClient - Main class for receiving location updates.
    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    // LocationRequest - Requirements for the location updates, i.e.,
// how often you should receive updates, the priority, etc.
    private lateinit var locationRequest: LocationRequest

    // LocationCallback - Called when FusedLocationProviderClient
// has a new Location
    private lateinit var locationCallback: LocationCallback

    // This will store current location info
    private var currentLocation: Location? = null

    var callback:onBackPressedListener?=null



    private val viewModel by viewModels<HomeViemodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        navController=findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        val nav_menu=binding.bottomNavView.menu
        val home=nav_menu.findItem(R.id.homeFragment)
        home.setOnMenuItemClickListener {

            navController.navigate(R.id.homeFragment)
            false
        }


        viewModel.getCategories()
        viewModel.getPlaces()
        viewModel.getLanguages()
        observeData()

        initializeLocation()







    }

    fun initializeLocation(){
        locationRequest = LocationRequest().apply {

            interval = TimeUnit.SECONDS.toMillis(60)

            // Sets the fastest rate for active location updates.
            // This interval is exact, and your application will never
            // receive updates more frequently than this value
            fastestInterval = TimeUnit.SECONDS.toMillis(30)

            // Sets the maximum time when batched location
            // updates are delivered. Updates may be
            // delivered sooner than this interval
            maxWaitTime = TimeUnit.MINUTES.toMillis(2)

            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                locationResult?.lastLocation?.let {
                    currentLocation = it
                    val latitude = currentLocation?.latitude
                    val longitude= currentLocation?.longitude
                    preferenceManger.saveLat(latitude.toString())
                    preferenceManger.saveLon(longitude.toString())
                    val geocode=Geocoder(this@MainActivity)
                    val address=geocode.getFromLocation(latitude!!,longitude!!,10)

                    // use latitude and longitude as per your need
                } ?: {
//                    Log.d(TAG, "Location information isn't available.")
                }
            }
        }

        if (isLocationPermissionGranted()){

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        }

    }

    private fun observeData(){
        viewModel.responseCategory.observe(this, Observer {
            when(it){
                is ResponceState.Succes -> {
                    viewModel.insertCategoryData(it.result.data)
                }
                else -> {

                }
            }
        })
        viewModel.placeLiveData.observe(this, Observer {
            when(it){
                is ResponceState.Succes -> {
                    viewModel.insertPlaces(it.result.data)
                }
                else -> {

                }
            }
        })

        viewModel.response_language.observe(this, Observer {
            when(it){

                is ResponceState.Succes->{
                    viewModel.insertLanguage(it.result.data)
                }
                else -> {

                }
            }
        })

//        viewModel.getAllPlces().observe(this, Observer {
//            Log.e("TAG", "observeData: $it")
//        })


    }




    override fun onBackPressed() {
        if (fragmentName.equals("SETTIGS")|| fragmentName.equals("PROFILE")||
            fragmentName.equals("SUPPORT")){
            navController.navigate(R.id.homeFragment)
        }else if (fragmentName.equals("SHOP")){
            navController.popBackStack(R.id.shopfragment, true);
            navController.navigate(R.id.homeFragment);
        }else if (fragmentName.equals("UPDATE")){
            navController.navigateUp()
        }else if(fragmentName.equals("HOME")){
            callback?.onBackPressed()
        }else{
            super.onBackPressed()
        }

    }
    companion object{
        var fragmentName="HOME"

    }


    fun setOnBackPressedListenter(onBackPressedListener: onBackPressedListener){
        this.callback=onBackPressedListener
    }





}