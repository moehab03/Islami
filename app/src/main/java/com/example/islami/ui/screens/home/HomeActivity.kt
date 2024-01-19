package com.example.islami.ui.screens.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.ActivityHomeBinding
import com.example.islami.ui.screens.ahadeth.AhadethFragment
import com.example.islami.ui.screens.quran.QuranFragment
import com.example.islami.ui.screens.radio.RadioFragment
import com.example.islami.ui.screens.sebha.SebhaFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startFragment(QuranFragment())
        pressItem()
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }

    private fun pressItem(){
        binding.bottomNavBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.quranMenuItem -> {
                    startFragment(QuranFragment())

                }
                R.id.radioMenuItem -> {
                    startFragment(RadioFragment())
                }
                R.id.tasbehMenuItem -> {
                    startFragment(SebhaFragment())
                }
                R.id.ahadethMenuItem -> {
                    startFragment(AhadethFragment())
                }
            }

            return@setOnItemSelectedListener true
        }
    }

}