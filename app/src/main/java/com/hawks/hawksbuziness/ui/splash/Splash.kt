package com.hawks.hawksbuziness.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.ui.activity.ReferalActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class Splash : AppCompatActivity() {

    @Inject
    lateinit var preferenceManger: PreferenceManger

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(mainLooper).postDelayed(Runnable {

            loadNextpage()

        },1000)

    }fun loadNextpage(){
        if (preferenceManger.getCountry().isNullOrEmpty()){
            startActivity(Intent(this, ReferalActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }


}