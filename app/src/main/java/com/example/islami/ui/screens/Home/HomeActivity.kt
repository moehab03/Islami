package com.example.islami.ui.screens.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.islami.R
import com.example.islami.ui.screens.ahadeth.AhadethFragment
import com.example.islami.ui.screens.sebha.SebhaFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, SebhaFragment())
            .commit()
    }
}