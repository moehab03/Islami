package com.example.islami.ui.screens.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.islami.Constant
import com.example.islami.databinding.ActivityDetailsBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkQuranOrHadeth()
    }

    private fun checkQuranOrHadeth() {
        val details = intent.getIntExtra(Constant.DETAILS, 0)
        if (details == 1) {
            quranDetails()
        } else if (details == 2) {
            hadethDetails()
        }
    }
    private fun quranDetails() {
        val suraName = intent.getStringExtra(Constant.SURA_NAME)!!
        binding.detailsLabelTV.text = "سورة $suraName"
        binding.detailsTV.text = readQuranFile()
    }

    private fun readQuranFile(): String {
        val fileName = intent.getStringExtra(Constant.FILE_NAME)!!
        var fileContent = ""
        try {
            val inputStream = assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))

            val fileLines: List<String> = reader.readLines()
            fileContent = fileLines.joinToString(" ") {
                return@joinToString it + "( ${fileLines.indexOf(it) + 1} )"
            }
            reader.close()
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return fileContent
    }

    private fun hadethDetails() {
        binding.quranPlayBtn.isVisible = false
    }
}