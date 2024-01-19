package com.example.islami.ui.screens.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islami.Constant
import com.example.islami.R
import com.example.islami.adapter.SurasAdapter
import com.example.islami.databinding.ActivityHomeBinding
import com.example.islami.databinding.FragmentAhadethBinding
import com.example.islami.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {

    lateinit var binding: FragmentQuranBinding
    lateinit var adapter: SurasAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SurasAdapter(Constant.SURAS_NAMES)
        binding.suraRecyvlerView.adapter = adapter
    }

}