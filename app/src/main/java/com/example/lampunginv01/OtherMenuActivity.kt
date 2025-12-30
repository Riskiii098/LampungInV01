package com.example.lampunginv01

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lampunginv01.databinding.ActivityOtherMenuBinding

class OtherMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtherMenuBinding

    // Konstanta untuk mengidentifikasi tab mana yang aktif
    companion object {
        const val EXTRA_INITIAL_TAB = "extra_initial_tab"
        const val TAB_UNGGULAN = 0
        const val TAB_KATEGORI = 1
        
        fun start(context: Context, initialTab: Int = TAB_UNGGULAN) {
            val intent = Intent(context, OtherMenuActivity::class.java).apply {
                putExtra(EXTRA_INITIAL_TAB, initialTab)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Handle Edge-to-Edge and padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
        
        // Cek intent awal mau buka tab apa
        val initialTab = intent.getIntExtra(EXTRA_INITIAL_TAB, TAB_UNGGULAN)
        switchTab(initialTab)
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.tabUnggulan.setOnClickListener {
            switchTab(TAB_UNGGULAN)
        }

        binding.tabKategori.setOnClickListener {
            switchTab(TAB_KATEGORI)
        }
    }

    private fun switchTab(tabIndex: Int) {
        val activeColor = Color.BLACK
        val inactiveColor = Color.DKGRAY
        
        if (tabIndex == TAB_UNGGULAN) {
            // UI Update for Tab Unggulan Active
            binding.tvTabUnggulan.setTextColor(activeColor)
            binding.tvTabUnggulan.setTypeface(null, Typeface.BOLD)
            binding.indicatorUnggulan.visibility = View.VISIBLE
            
            binding.tvTabKategori.setTextColor(inactiveColor)
            binding.tvTabKategori.setTypeface(null, Typeface.NORMAL)
            binding.indicatorKategori.visibility = View.INVISIBLE
            
            // Load Fragment Unggulan
            loadFragment(UnggulanFragment())
        } else {
            // UI Update for Tab Kategori Active
            binding.tvTabUnggulan.setTextColor(inactiveColor)
            binding.tvTabUnggulan.setTypeface(null, Typeface.NORMAL)
            binding.indicatorUnggulan.visibility = View.INVISIBLE
            
            binding.tvTabKategori.setTextColor(activeColor)
            binding.tvTabKategori.setTypeface(null, Typeface.BOLD)
            binding.indicatorKategori.visibility = View.VISIBLE
            
            // Load Fragment Kategori
            loadFragment(KategoriFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.contentContainer.id, fragment)
            .commit()
    }
}