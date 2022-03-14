package com.hawks.hawksbuziness.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.ui.activity.ReferalActivity
import kotlinx.coroutines.*

class Splash : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        CoroutineScope(Dispatchers.Main).launch {
            loadNextpage()
        }
    }
    suspend fun loadNextpage(){
        delay(3000)
        startActivity(Intent(this, ReferalActivity::class.java))
        finish()
    }
}