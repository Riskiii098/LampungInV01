package com.example.lampunginv01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class InfoCard(val title: String, val description: String)

class InfoCardAdapter(private val cards: List<InfoCard>) : RecyclerView.Adapter<InfoCardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tv_card_title)
        val description: TextView = view.findViewById(R.id.tv_card_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.title.text = card.title
        holder.description.text = card.description
    }

    override fun getItemCount() = cards.size
}