package com.example.lampunginv01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpandableCategoryAdapter(
    private val items: List<ExpandableItem>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ExpandableCategoryAdapter.ViewHolder>() {

    data class ExpandableItem(
        val title: String,
        val description: String,
        var isExpanded: Boolean = false
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val ivArrow: ImageView = view.findViewById(R.id.iv_arrow)
        val tvDescription: TextView = view.findViewById(R.id.tv_description)
        val expandableLayout: LinearLayout = view.findViewById(R.id.expandable_layout)
        val headerContainer: View = view.findViewById(R.id.header_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expandable_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description

        // Atur visibilitas berdasarkan status isExpanded
        if (item.isExpanded) {
            holder.expandableLayout.visibility = View.VISIBLE
            holder.ivArrow.rotation = 180f
        } else {
            holder.expandableLayout.visibility = View.GONE
            holder.ivArrow.rotation = 0f
        }

        // Logic 1: Klik Panah -> Expand/Collapse
        holder.ivArrow.setOnClickListener {
            item.isExpanded = !item.isExpanded
            notifyItemChanged(position)
        }

        // Logic 2: Klik Header/Body (Selain Panah) -> Pindah Halaman
        holder.headerContainer.setOnClickListener {
            onItemClick(item.title)
        }
        
        // Agar klik panah tidak tembus ke header container (opsional, tapi good practice)
        holder.ivArrow.isClickable = true
        holder.ivArrow.isFocusable = true
    }

    override fun getItemCount() = items.size
}