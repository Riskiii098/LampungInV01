package com.example.lampunginv01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lampunginv01.databinding.ItemRecommendationBinding

class RecommendationAdapter(
    private val items: List<RecommendationModel>,
    private val onItemClick: (RecommendationModel) -> Unit
) : RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    class RecommendationViewHolder(val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecommendationModel, onItemClick: (RecommendationModel) -> Unit) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.ivRecommendation.setImageResource(item.imageRes)
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val binding = ItemRecommendationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecommendationViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }
}