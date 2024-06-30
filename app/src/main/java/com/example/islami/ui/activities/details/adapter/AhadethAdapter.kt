package com.example.islami.ui.activities.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.islami.R
import com.example.islami.ui.data_models.AhadethModel

class AhadethAdapter(private var ahadethList: MutableList<AhadethModel>) :
    Adapter<AhadethAdapter.ViewHolder>() {

        lateinit var onHadethClick: OnItemCLickListner

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hadeth: TextView = itemView.findViewById(R.id.hadethTv)
    }

    interface OnItemCLickListner {
        fun onItemClick(hadeth : AhadethModel, index: Int)
    }

    override fun getItemCount(): Int = ahadethList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.ahadeth_view, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hadeth = ahadethList[position]
        holder.hadeth.text = hadeth.title
        holder.itemView.setOnClickListener {
            onHadethClick.onItemClick(hadeth, position)
        }
    }
}