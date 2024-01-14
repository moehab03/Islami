package com.example.islami.ui.screens.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.islami.databinding.ActivitySplashScreenBinding
import com.example.islami.ui.screens.Home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed({
            startHomeScreen()
            finish()
        }, 2000)
    }

    private fun startHomeScreen() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

}