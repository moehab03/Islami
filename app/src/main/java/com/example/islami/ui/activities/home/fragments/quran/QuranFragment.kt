package com.example.islami.ui.activities.home.fragments.quran

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.ui.base.BaseFragment
import com.example.islami.ui.utils.Constant
import com.example.islami.databinding.FragmentQuranBinding
import com.example.islami.ui.activities.details.DetailsActivity
import com.example.islami.ui.activities.details.adapter.SurasAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranFragment : BaseFragment<FragmentQuranBinding>() {

    private lateinit var surasAdapter: SurasAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        surasAdapter = SurasAdapter(Constant.SURAS_NAMES_LIST)

        surasAdapter.onSuraClick = object : SurasAdapter.OnItemCLickListner {
            override fun onItemClick(suraName: String, index: Int) {
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(Constant.SURA_NAME, suraName)
                intent.putExtra(Constant.FILE_NAME, "${index + 1}.txt")
                intent.putExtra(Constant.DETAILS, 1)
                startActivity(intent)
            }

        }
        binding.suraRecyvlerView.adapter = surasAdapter
    }

    override fun getLayoutId(): Int = R.layout.fragment_quran
}