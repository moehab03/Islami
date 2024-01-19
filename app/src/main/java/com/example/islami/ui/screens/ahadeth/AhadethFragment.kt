package com.example.islami.ui.screens.ahadeth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islami.adapter.AhadethAdapter
import com.example.islami.databinding.FragmentAhadethBinding

class AhadethFragment : Fragment() {

    private lateinit var binding: FragmentAhadethBinding
    lateinit var adapter : AhadethAdapter
    private var ahadethList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAhadethBinding.inflate(inflater, container, false)
        fillAhadethArray()
        adapter = AhadethAdapter(ahadethList)
        binding.ahadethRecyclerView.adapter = adapter
        return binding.root
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun fillAhadethArray() {
        for (x in 1..55) {
            ahadethList.add("الحديث رقم $x")
        }
    }

}