package com.example.lampunginv01

import android.view.Gravity
import android.view.LayoutInflater
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

        fun bind(item: MenuModel) {
            binding.tvMenuName.text = item.title
            binding.ivIcon.setImageResource(item.iconRes)
            
            // Pastikan root memiliki width match parent
            (binding.root.layoutParams as? ViewGroup.MarginLayoutParams)?.width = ViewGroup.LayoutParams.MATCH_PARENT
            
            // Klik pada container dalam (yang berisi gambar dan teks)
            binding.clickContainer.setOnClickListener {
                onItemClick(item)
            }
            
            // Opsional: Klik pada root juga bisa ditambahkan jika area klik ingin lebih luas
            // Tapi karena kita punya clickContainer dengan ukuran fixed, lebih baik di situ saja agar efek ripple rapi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemFeatureMenuBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        // Set width MATCH_PARENT agar mengisi kolom grid yang dibagi rata oleh GridLayoutManager
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(items[position])
    }
}