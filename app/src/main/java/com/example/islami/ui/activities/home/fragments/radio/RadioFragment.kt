package com.example.islami.ui.activities.home.fragments.radio

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.fragment.app.viewModels
import com.example.islami.R
import com.example.islami.R.drawable.ic_pause
import com.example.islami.R.drawable.ic_play
import com.example.islami.R.drawable.radio_ic
import com.example.islami.databinding.FragmentRadioBinding
import com.example.islami.ui.activities.home.HomeActivity
import com.example.islami.ui.base.BaseFragment
import com.example.islami.ui.data_models.RadiosItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioFragment : BaseFragment<FragmentRadioBinding>() {
    private var currentChannel: Int? = null
    private var mediaPlayer = MediaPlayer()
    private var radioList = listOf<RadiosItem?>()
    private val vm: RadioViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        vm.getRadiosList()
        startForegroundService()
        observeToLiveData()
        playAndPauseRadio()
        nextChannel()
        preveousChannel()
    }

    private fun startForegroundService(){
        val intent = Intent(requireContext(),RadioService::class.java)
        requireActivity().startService(intent)
    }

    private fun observeToLiveData() {
        vm.radioList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                radioList = it
                setMediaPlayerDataSource(radioList[0]?.url ?: "")
                currentChannel = 0
                updateRadioName()
                binding.progressBar.visibility = View.GONE
                binding.radioPlayBtn.visibility = View.VISIBLE
            }
        }
    }

    private fun setMediaPlayerDataSource(url: String) {
        mediaPlayer.apply {
            reset()
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener {
                start()
                binding.radioPlayBtn.setBackgroundResource(ic_pause)
            }
        }

    }

    private fun updateRadioName() {
        binding.radioTV.text = radioList[currentChannel!!]?.name
    }

    private fun nextChannel() {
        binding.radioNextBtn.setOnClickListener {
            currentChannel = currentChannel!! + 1
            updateRadioName()
            setMediaPlayerDataSource(radioList[currentChannel!!]?.url ?: "")
        }
    }


    private fun preveousChannel() {
        binding.radioPreveousBtn.setOnClickListener {
            if (currentChannel != null && currentChannel!! > 0) {
                currentChannel = currentChannel!! - 1
                updateRadioName()
                setMediaPlayerDataSource(radioList[currentChannel!!]?.url ?: "")
            } else
                currentChannel = radioList.size - 1
        }
    }

    private fun playAndPauseRadio() {
        binding.radioPlayBtn.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.radioPlayBtn.setBackgroundResource(ic_play)
            } else {
                mediaPlayer.start()
                binding.radioPlayBtn.setBackgroundResource(ic_pause)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.reset()
        binding.radioPlayBtn.setBackgroundResource(ic_play)
    }

    override fun onResume() {
        super.onResume()
        if (currentChannel != null)
            setMediaPlayerDataSource(radioList[currentChannel!!]?.url ?: "")
    }

    override fun getLayoutId(): Int = R.layout.fragment_radio
}