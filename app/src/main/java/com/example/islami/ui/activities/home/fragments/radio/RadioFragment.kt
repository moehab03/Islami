package com.example.islami.ui.activities.home.fragments.radio

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.example.islami.R
import com.example.islami.R.drawable.ic_pause
import com.example.islami.R.drawable.ic_play
import com.example.islami.databinding.FragmentRadioBinding
import com.example.islami.ui.base.BaseFragment
import com.example.islami.ui.data_models.RadioStation
import com.example.islami.ui.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioFragment : BaseFragment<FragmentRadioBinding>() {
    private var radioStations = listOf<RadioStation?>()
    private var currentChannel: Int? = null
    private var mediaPlayerState = false
    private val vm: RadioViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        loadRadiosList()
        observeToLiveData()
        binding.radioPlayBtn.setOnClickListener { playAndPauseRadio() }
        binding.radioNextBtn.setOnClickListener { nextChannel() }
        binding.radioPreveousBtn.setOnClickListener { preveousChannel() }
    }

    private fun loadRadiosList() {
        if (radioStations.isEmpty())
            vm.getRadiosList()
    }

    private fun observeToLiveData() {
        vm.radioStations.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                radioStations = it
                updateUI()
                createService()
            }
        }
    }

    private fun updateUI() {
        mediaPlayerState = true
        currentChannel = 0
        updateRadioName()
        binding.radioPlayBtn.setBackgroundResource(ic_pause)
        binding.progressBar.visibility = View.GONE
        binding.radioPlayBtn.visibility = View.VISIBLE
    }

    private fun createService() {
        val intent = Intent(requireContext(), RadioService::class.java)
        intent.putParcelableArrayListExtra(Constant.RADIOS_STATION, ArrayList(radioStations))
        intent.putExtra(Constant.CURRENT_INDEX, 0)
        requireActivity().startService(intent)
    }

    private fun updateService(index: Int) {
        val intent = Intent(requireContext(), RadioService::class.java)
        intent.putExtra(Constant.CURRENT_INDEX, index)
        requireActivity().startService(intent)
    }

    private fun updateRadioName() {
        binding.radioTV.text = radioStations[currentChannel!!]?.name
    }

    private fun nextChannel() {
        currentChannel = if (currentChannel != null && currentChannel!! < (radioStations.size - 1) )
            currentChannel!! + 1
        else
            0

        updateRadioName()
        updateService(currentChannel!!)
    }

    private fun preveousChannel() {
        currentChannel = if (currentChannel != null && currentChannel!! > 0) {
            currentChannel!! - 1
        } else
            radioStations.size - 1

        updateRadioName()
        updateService(currentChannel!!)

    }

    private fun playAndPauseRadio() {
        if (mediaPlayerState) {
            mediaPlayerState = false
            updateService(-1)
            binding.radioPlayBtn.setBackgroundResource(ic_play)
        } else {
            mediaPlayerState = true
            updateService(currentChannel!!)
            binding.radioPlayBtn.setBackgroundResource(ic_pause)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        destroyService()
    }

    private fun destroyService() {
        val intent = Intent(requireContext(), RadioService::class.java)
        requireActivity().stopService(intent)
    }

    override fun getLayoutId(): Int = R.layout.fragment_radio
}