package com.example.islami.ui.activities.home.fragments.ahadeth

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.islami.R
import com.example.islami.ui.base.BaseFragment
import com.example.islami.databinding.FragmentAhadethBinding
import com.example.islami.ui.activities.details.DetailsActivity
import com.example.islami.ui.activities.details.adapter.AhadethAdapter
import com.example.islami.ui.data_models.AhadethModel
import com.example.islami.ui.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
@AndroidEntryPoint
class AhadethFragment : BaseFragment<FragmentAhadethBinding>() {

    private lateinit var adapter: AhadethAdapter
    private var ahadethList = mutableListOf<AhadethModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        readAhadeth()
        adapter = AhadethAdapter(ahadethList)
        binding.ahadethRecyclerView.adapter = adapter
        adapter.onHadethClick = object : AhadethAdapter.OnItemCLickListner {
            override fun onItemClick(hadeth: AhadethModel, index: Int) {
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(Constant.DETAILS, 2)
                intent.putExtra(Constant.HADETH, hadeth)
                startActivity(intent)
            }

        }
    }

    private fun readAhadeth() {
        try {
            val inputStream = activity?.assets!!.open("ahadeeth.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))

            val ahadeth = reader.readText()

            val ahadethList: List<String> = ahadeth.split("#")
            for (hadeth in ahadethList) {
                val singleHadeth = hadeth.trim()
                val singleHadethLines: MutableList<String> =
                    singleHadeth.split("\n").toMutableList()
                val hadethTitle = singleHadethLines[0]
                singleHadethLines.removeAt(0)
                val hadethBody = singleHadethLines.joinToString(" ") { return@joinToString it }

                this.ahadethList.add(AhadethModel(title = hadethTitle, body = hadethBody))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_ahadeth
}