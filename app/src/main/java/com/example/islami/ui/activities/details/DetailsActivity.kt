package com.example.islami.ui.activities.details

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.islami.R
import com.example.islami.databinding.ActivityDetailsBinding
import com.example.islami.ui.data_models.AhadethModel
import com.example.islami.ui.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
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
        val suraName = "سورة${intent.getStringExtra(Constant.SURA_NAME)!!}"
        binding.detailsLabelTV.text = suraName
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

    @Suppress("DEPRECATION")
    private fun hadethDetails() {
        val hadeth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(Constant.HADETH, AhadethModel::class.java)
        } else {
            intent.getSerializableExtra(Constant.HADETH) as AhadethModel
        }!!

        binding.detailsLabelTV.text = hadeth.title
        binding.detailsTV.text = hadeth.body
    }
}