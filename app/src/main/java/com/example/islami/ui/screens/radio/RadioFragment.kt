package com.example.islami.ui.screens.radio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.islami.R.drawable.ic_pause
import com.example.islami.R.drawable.ic_play
import com.example.islami.databinding.FragmentRadioBinding

class RadioFragment : Fragment() {
    private lateinit var binding: FragmentRadioBinding
    private var radioState = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playAndPauseRadio()
        nextChannel()
        preveousChannel()
    }

    private fun nextChannel() {
        binding.radioNextBtn.setOnClickListener {
            Toast.makeText(activity, "Next button pressed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun preveousChannel() {
        binding.radioPreveousBtn.setOnClickListener {
            Toast.makeText(activity, "Preveous button pressed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun playAndPauseRadio() {
        binding.radioPlayBtn.setOnClickListener {
            if (!radioState) {
                radioState = true
                binding.radioPlayBtn.setBackgroundResource(ic_pause)
            }else{
                radioState = false
                binding.radioPlayBtn.setBackgroundResource(ic_play)
            }
        }
    }


}