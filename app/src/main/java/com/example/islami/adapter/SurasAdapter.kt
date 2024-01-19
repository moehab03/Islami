package com.example.islami.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.R

class SurasAdapter(private var surasList: ArrayList<String>) :
    RecyclerView.Adapter<SurasAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hadeth: TextView = itemView.findViewById(R.id.hadethTv)
    }

    override fun getItemCount(): Int = surasList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.ahadeth_view, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hadeth.text = surasList[position]
    }
}