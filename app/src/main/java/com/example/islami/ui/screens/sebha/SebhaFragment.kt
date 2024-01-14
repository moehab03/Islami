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

class SebhaFragment : Fragment() {
    
    private var azkarList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_sebha, container, false)

        fillArray()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun fillArray() {
        azkarList.add("سبحان الله")
        azkarList.add("الحمدلله")
        azkarList.add("الله اكبر")
        azkarList.add("لا اله الا الله وحده لا شريك له له الملك وله الحمد وهو علي كل شيء قدير")
        azkarList.add("اللهم اعني علي ذكرك و شكرك و حسن عبادتك")
        azkarList.add("اللهم صلي وسلم وبارك علي سيدنا محمد")
        azkarList.add("استغفرك ربي و اتوب إليك")
    }
}