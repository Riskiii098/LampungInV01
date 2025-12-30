package com.example.lampunginv01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lampunginv01.databinding.ItemSliderBannerBinding

class BannerAdapter(private val banners: List<BannerModel>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(val binding: ItemSliderBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: BannerModel) {
            // Karena belum pakai library Glide/Coil, kita pakai setImageResource dulu
            binding.ivBanner.setImageResource(banner.imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemSliderBannerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int = banners.size

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(banners[position])
    }
}