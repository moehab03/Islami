package com.example.islami.ui.screens.quran

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islami.Constant
import com.example.islami.adapter.SurasAdapter
import com.example.islami.databinding.FragmentQuranBinding
import com.example.islami.ui.screens.details.DetailsActivity

class QuranFragment : Fragment() {

    lateinit var binding: FragmentQuranBinding
    lateinit var surasAdapter: SurasAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        surasAdapter = SurasAdapter(Constant.SURAS_NAMES_LIST)

        surasAdapter.onSuraClick = object : SurasAdapter.OnItemCLickListner {
            override fun onItemClick(suraName: String, index: Int) {
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(Constant.SURA_NAME,suraName)
                intent.putExtra(Constant.FILE_NAME,"${index+1}.txt")
                intent.putExtra(Constant.DETAILS,1)
                startActivity(intent)
            }

        }
        binding.suraRecyvlerView.adapter = surasAdapter
    }

}