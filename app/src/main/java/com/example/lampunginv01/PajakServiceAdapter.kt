package com.example.lampunginv01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PajakServiceAdapter(
    private val items: List<PajakServiceModel>,
    private val onItemClick: (PajakServiceModel) -> Unit = {}
) : RecyclerView.Adapter<PajakServiceAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvSubtitle: TextView = itemView.findViewById(R.id.tv_subtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pajak_service, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvSubtitle.text = item.subtitle
        holder.ivIcon.setImageResource(item.imageResId)
        
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}