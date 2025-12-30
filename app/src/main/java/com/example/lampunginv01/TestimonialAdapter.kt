package com.example.lampunginv01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lampunginv01.databinding.ItemTestimonialBinding

class TestimonialAdapter(private val testimonials: List<TestimonialModel>) :
    RecyclerView.Adapter<TestimonialAdapter.TestimonialViewHolder>() {

    class TestimonialViewHolder(val binding: ItemTestimonialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TestimonialModel) {
            binding.tvName.text = item.name
            binding.ratingBar.rating = item.rating
            binding.tvComment.text = item.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestimonialViewHolder {
        val binding = ItemTestimonialBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TestimonialViewHolder(binding)
    }

    override fun getItemCount(): Int = testimonials.size

    override fun onBindViewHolder(holder: TestimonialViewHolder, position: Int) {
        holder.bind(testimonials[position])
    }
}