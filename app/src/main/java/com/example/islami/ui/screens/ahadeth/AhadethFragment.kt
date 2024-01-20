package com.example.islami.ui.screens.ahadeth

import android.content.Intent
import android.os.Bundle
import android.telecom.Call.Details
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islami.Constant
import com.example.islami.adapter.AhadethAdapter
import com.example.islami.data_model.AhadethModel
import com.example.islami.databinding.FragmentAhadethBinding
import com.example.islami.ui.screens.details.DetailsActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class AhadethFragment : Fragment() {

    private lateinit var binding: FragmentAhadethBinding
    private lateinit var adapter: AhadethAdapter
    private var ahadethList = mutableListOf<AhadethModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAhadethBinding.inflate(inflater, container, false)
        return binding.root
    }

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
                intent.putExtra(Constant.DETAILS,2)
                intent.putExtra(Constant.HADETH,hadeth)
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


}