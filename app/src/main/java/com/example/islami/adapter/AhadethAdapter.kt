package com.example.islami.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.islami.R

class AhadethAdapter(private var ahadethList: ArrayList<String>) : Adapter<AhadethAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hadeth: TextView = itemView.findViewById(R.id.hadethTv)
    }

    override fun getItemCount(): Int = ahadethList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.ahadeth_view, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hadeth.text = ahadethList[position]
    }
}