package com.example.lampunginv01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lampunginv01.databinding.ItemNewsCategoryBinding

class NewsCategoryAdapter(
    private val categories: List<NewsCategoryModel>,
    private val onItemClick: (NewsCategoryModel) -> Unit
) : RecyclerView.Adapter<NewsCategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val binding: ItemNewsCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: NewsCategoryModel, onItemClick: (NewsCategoryModel) -> Unit) {
            binding.tvCategoryName.text = category.title
            binding.ivCategoryIcon.setImageResource(category.iconRes)
            binding.root.setOnClickListener { onItemClick(category) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemNewsCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position], onItemClick)
    }
}