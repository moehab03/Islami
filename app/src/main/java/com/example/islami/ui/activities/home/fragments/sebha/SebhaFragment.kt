package com.example.islami.ui.activities.home.fragments.sebha

import android.os.Bundle
import android.view.View
import com.example.islami.R
import com.example.islami.ui.base.BaseFragment
import com.example.islami.databinding.FragmentSebhaBinding
import com.example.islami.ui.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SebhaFragment : BaseFragment<FragmentSebhaBinding>() {
    private var azkarList = ArrayList<String>()
    private var counter = 1
    private var azkarCounter = 1
    private var currentRotation = 0f

    override fun getLayoutId(): Int = R.layout.fragment_sebha
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSebha()
        tasbih()
    }

    private fun initSebha() {
        azkarList = Constant.AZKAR_LIST
        binding.counterBtn.text = counter.toString()
        binding.zekrBtn.text = azkarList[0]
    }

    private fun rotateImage() {
        currentRotation += 180f
        binding.sebhaBodyIv.rotation = currentRotation
    }

    private fun changeZekr() {
        if (azkarCounter == azkarList.size)
            azkarCounter = 0

        binding.zekrBtn.text = azkarList[azkarCounter]
        azkarCounter++
    }

    private fun tasbih() {
        binding.sebhaBodyIv.setOnClickListener {
            increaseCounter()
        }
        binding.zekrBtn.setOnClickListener {
            increaseCounter()
        }
        binding.counterBtn.setOnClickListener {
            increaseCounter()
        }
    }

    private fun increaseCounter() {
        if (counter % 33 == 0)
            changeZekr()

        counter++
        rotateImage()
        binding.counterBtn.text = counter.toString()
    }

}