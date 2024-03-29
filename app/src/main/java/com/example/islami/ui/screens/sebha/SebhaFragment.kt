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
    lateinit var binding: FragmentSebhaBinding
    private var azkarList = ArrayList<String>()
    private var counter = 1
    private var azkarCounter = 1
    private var currentRotation = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fillArray()
        binding = FragmentSebhaBinding.inflate(inflater, container, false)
        binding.counterBtn.text = counter.toString()
        binding.zekrTv.text = azkarList[0]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.counterBtn.setOnClickListener {
            increaseCounter()

            rotateImage()
        }
    }

    private fun rotateImage() {
        currentRotation += 45f
        val matrix = android.graphics.Matrix()
        binding.sebhaBodyIv.scaleType = ImageView.ScaleType.MATRIX
        matrix.postRotate(currentRotation, binding.sebhaBodyIv.width.toFloat() / 2, binding.sebhaBodyIv.height.toFloat() / 2)
        binding.sebhaBodyIv.imageMatrix = matrix
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
        binding.counterBtn.text = counter.toString()
    }

    private fun changeZekr() {
        if (azkarCounter == azkarList.size)
            azkarCounter = 0

        binding.zekrTv.text = azkarList[azkarCounter]
        azkarCounter++
    }
}