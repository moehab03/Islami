package com.example.islami.ui.activities.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.R

class SurasAdapter(private var surasList: ArrayList<String>) :
    RecyclerView.Adapter<SurasAdapter.ViewHolder>() {

    var onSuraClick: OnItemCLickListner? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sura: TextView = itemView.findViewById(R.id.hadethTv)
    }

    interface OnItemCLickListner {
        fun onItemClick(suraName: String, index: Int)
    }

    override fun getItemCount(): Int = surasList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.ahadeth_view, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.sura.text = surasList[position]

        if (onSuraClick != null) {
            holder.itemView.setOnClickListener {
                onSuraClick!!.onItemClick(surasList[position], position)
            }
        }

    }
}