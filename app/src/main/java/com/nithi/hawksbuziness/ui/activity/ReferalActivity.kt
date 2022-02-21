package com.nithi.hawksbuziness.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nithi.hawksbuziness.R
import com.nithi.hawksbuziness.databinding.ActivityReferalBinding

class ReferalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReferalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= DataBindingUtil.setContentView(this,R.layout.activity_referal)
        binding.submit.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}