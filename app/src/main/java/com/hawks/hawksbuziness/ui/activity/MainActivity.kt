package com.hawks.hawksbuziness.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.*
import com.google.gson.Gson
import com.hawks.hawksbuziness.databinding.ActivityMainBinding
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.home.HomeViemodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.model.info.Data
import com.hawks.hawksbuziness.utill.*


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

    var callback: onBackPressedListener? = null

    private val viewModel by viewModels<HomeViemodel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        val nav_menu = binding.bottomNavView.menu
        val home = nav_menu.findItem(R.id.homeFragment)
        val profile = nav_menu.findItem(R.id.profile)


        home.setOnMenuItemClickListener {

            navController.navigate(R.id.homeFragment)
            false
        }

        profile.setOnMenuItemClickListener {
            if (!preferenceManger.getUserId().isNullOrEmpty()) {
                navController.navigate(R.id.profileFragment)
            } else {
                startActivity(Intent(this, SignupActivity::class.java))

            }
            false
        }


        viewModel.getCategories()
        viewModel.getPlaces()
        viewModel.getLanguages()

//        viewModel.getContactData(this)
        observeData()

        initializeLocation()


        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                100
            )
        } else {
            getContacts()
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts()
            } else {
                Toast.makeText(this, "The app was not allowed to read your contact", Toast.LENGTH_LONG).show();
            }
        }

    }


    fun getContacts() {
        MainScope().launch {
            val list = getContactList()
            val gson = Gson()
            val contactsJson = gson.toJson(list).toString()
            val userId=if (preferenceManger.getUserId().isNullOrEmpty()) "" else preferenceManger.getUserId()
            if (list.isNotEmpty()){

                viewModel.uploadContacts(contactsJson,userId).observe(this@MainActivity, Observer {
                  when(it){
                      is ResponceState.Failiure -> {

                      }
                      is ResponceState.Loading -> {

                      }
                      is ResponceState.Succes ->{

                      }
                  }
                })
            }

        }

    }


    fun initializeLocation() {
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
                    val longitude = currentLocation?.longitude
                    preferenceManger.saveLat(latitude.toString())
                    preferenceManger.saveLon(longitude.toString())
//                    val geocode=Geocoder(this@MainActivity)
//                    val address=geocode.getFromLocation(latitude!!,longitude!!,10)

//                    Log.e("TAG", "onLocationResult: "+address )

                    // use latitude and longitude as per your need
                } ?: {
//                    Log.d(TAG, "Location information isn't available.")
                }
            }
        }

        if (isLocationPermissionGranted()) {

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
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        }

    }

    private fun observeData() {
        viewModel.responseCategory.observe(this, Observer {
            when (it) {
                is ResponceState.Succes -> {
                    viewModel.insertCategoryData(it.result.data)
                }
                else -> {

                }
            }
        })
        viewModel.placeLiveData.observe(this, Observer {
            when (it) {
                is ResponceState.Succes -> {
                    viewModel.insertPlaces(it.result.data)
                }
                else -> {

                }
            }
        })

        viewModel.response_language.observe(this, Observer {
            when (it) {

                is ResponceState.Succes -> {
                    viewModel.insertLanguage(it.result.data)
                }
                else -> {

                }
            }
        })


        viewModel.getInfo().observe(this, Observer {
            when (it) {
                is ResponceState.Failiure -> {
                }
                is ResponceState.Loading -> {
                }
                is ResponceState.Succes -> {
                    saveData(it.result.data)
                }
            }
        })


    }

    private fun saveData(data: Data) {
        preferenceManger.saveTerms(data.terms)
        preferenceManger.savePrivacy(data.privacy)
        preferenceManger.saveNumber(data.mobile)
        preferenceManger.saveEmail(data.email)
        preferenceManger.saveWANumber(data.whatsapp)
    }


    override fun onBackPressed() {
        if (fragmentName.equals("SETTIGS") || fragmentName.equals("PROFILE") ||
            fragmentName.equals("SUPPORT")
        ) {
            navController.navigate(R.id.homeFragment)
        } else if (fragmentName.equals("SHOP")) {
            navController.popBackStack(R.id.shopfragment, true);
            navController.navigate(R.id.homeFragment);
        } else if (fragmentName.equals("UPDATE")) {
            navController.navigateUp()
        } else if (fragmentName.equals("HOME")) {
            callback?.onBackPressed()
        } else {
            super.onBackPressed()
        }

    }

    companion object {
        var fragmentName = "HOME"

    }


    fun setOnBackPressedListenter(onBackPressedListener: onBackPressedListener) {
        this.callback = onBackPressedListener
    }


}