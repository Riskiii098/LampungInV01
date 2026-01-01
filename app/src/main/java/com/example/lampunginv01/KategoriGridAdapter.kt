package com.example.lampunginv01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lampunginv01.databinding.ItemKategoriGridBinding

class KategoriGridAdapter(
    private val list: List<MenuModel>,
    private val onClick: (MenuModel) -> Unit
) : RecyclerView.Adapter<KategoriGridAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemKategoriGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuModel) {
            binding.tvTitle.text = item.title
            binding.ivIcon.setImageResource(item.iconRes)
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemKategoriGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}