package com.example.lampunginv01

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lampunginv01.databinding.ItemFeatureMenuBinding

class MenuAdapter(
    private val items: List<MenuModel>,
    private val onItemClick: (MenuModel) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val binding: ItemFeatureMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuModel, position: Int) {
            binding.tvMenuName.text = item.title
            binding.ivIcon.setImageResource(item.iconRes)

            // Pastikan Gravity Center (Reset manual jika sebelumnya diubah)
            (binding.root as? LinearLayout)?.gravity = Gravity.CENTER

            // Set gravity menjadi TOP agar gambar sejajar di atas
            (binding.root as? LinearLayout)?.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL

            // Hitung TranslationX untuk mengatur jarak antar menu agar presisi
            // Menu 1 rata kiri search bar, Menu 5 rata kanan search bar, sisanya rata tengah
            binding.root.post {
                val parent = binding.root.parent as? View
                if (parent != null && parent.width > 0) {
                    val totalWidth = parent.width.toFloat()
                    val itemCount = items.size
                    
                    if (itemCount > 1) {
                        val cellWidth = totalWidth / itemCount
                        // Gunakan lebar icon sebenarnya atau default 56dp (konversi kasar jika belum terukur)
                        val iconWidth = if (binding.ivIcon.width > 0) binding.ivIcon.width.toFloat() else 40f * binding.root.context.resources.displayMetrics.density
                        
                        // Rumus: Shift linear dari -MaxOffset (kiri) ke +MaxOffset (kanan)
                        // MaxOffset adalah selisih setengah cell dengan setengah icon
                        // Shift = (Posisi - IndexTengah) * Step
                        
                        val centerIndex = (itemCount - 1) / 2f
                        val maxShift = (cellWidth - iconWidth) / 2f
                        
                        // Faktor interpolasi dari -1 (item pertama) sampai 1 (item terakhir)
                        val interpolation = (position - centerIndex) / centerIndex
                        
                        // Translation X
                        val translationX = interpolation * maxShift
                        
                        binding.root.translationX = translationX
                    }
                }
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemFeatureMenuBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        // Pastikan layout params match parent agar width kolom terisi penuh
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
}