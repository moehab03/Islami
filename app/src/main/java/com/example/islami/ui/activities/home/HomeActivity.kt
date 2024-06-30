package com.example.islami.ui.activities.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.ActivityHomeBinding
import com.example.islami.ui.activities.home.fragments.ahadeth.AhadethFragment
import com.example.islami.ui.activities.home.fragments.quran.QuranFragment
import com.example.islami.ui.activities.home.fragments.radio.RadioFragment
import com.example.islami.ui.activities.home.fragments.sebha.SebhaFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        startFragment(QuranFragment())
        pressItem()
    }

    private fun pressItem() {
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

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}