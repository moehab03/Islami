package com.example.islami.ui.screens.sebha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.islami.R
import com.example.islami.databinding.ActivityHomeBinding
import com.example.islami.databinding.FragmentSebhaBinding

class SebhaFragment : Fragment() {
    private lateinit var binding: FragmentSebhaBinding
    private var azkarList = ArrayList<String>()
    private var counter = 1
    private var azkarCounter = 1
    private var currentRotation = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSebhaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSebha()
        tasbeh()
    }

    private fun tasbeh() {
        binding.sebhaBodyIv.setOnClickListener {
            increaseCounter()
        }
        binding.counterBtn.setOnClickListener {
            increaseCounter()
        }
        binding.zekrBtn.setOnClickListener {
            increaseCounter()
        }
    }

    private fun initSebha() {
        fillArray()
        binding.counterBtn.text = counter.toString()
        binding.zekrBtn.text = azkarList[0]
    }

    private fun rotateImage() {
        currentRotation += 180f
        binding.sebhaBodyIv.rotation = currentRotation
    }

    private fun fillArray() {
        azkarList.add("سبحان الله")
        azkarList.add("الحمدلله")
        azkarList.add("الله اكبر")
        azkarList.add("اللهم اعني علي ذكرك و شكرك و حسن عبادتك")
        azkarList.add("اللهم صلي وسلم وبارك علي سيدنا محمد")
        azkarList.add("استغفرك ربي و اتوب إليك")
    }

    private fun increaseCounter() {
        if (counter % 33 == 0)
            changeZekr()

        counter++
        rotateImage()
        binding.counterBtn.text = counter.toString()
    }

    private fun changeZekr() {
        if (azkarCounter == azkarList.size)
            azkarCounter = 0

        binding.zekrBtn.text = azkarList[azkarCounter]
        azkarCounter++
    }
}