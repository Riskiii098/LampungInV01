package com.example.lampunginv01

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.FragmentActivityBinding

class ActivitySimulatedHistory : AppCompatActivity() {
    private lateinit var binding: FragmentActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        setupUI()
        setupListeners()
        updateData()
    }

    private fun setupListeners() {
        binding.layoutLaporanContent.cardCekLaporan.setOnClickListener {
            val intent = Intent(this, RiwayatLaporanActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupUI() {
        // Set Header Title
        binding.tvTitle.text = "Aktivitas"

        // Set Tab Laporan Saya Active
        binding.tvTabLaporan.setTextColor(Color.BLACK)
        binding.tvTabLaporan.setTypeface(null, Typeface.BOLD)
        binding.indicatorLaporan.visibility = View.VISIBLE

        binding.tvTabDisimpan.setTextColor(Color.DKGRAY)
        binding.tvTabDisimpan.setTypeface(null, Typeface.NORMAL)
        binding.indicatorDisimpan.visibility = View.INVISIBLE

        binding.layoutLaporanContent.root.visibility = View.VISIBLE
        binding.layoutDisimpanContent.visibility = View.GONE
    }

    private fun updateData() {
        // Update Counts to 1
        binding.layoutLaporanContent.tvStatTotalDibuat.text = "1"
        binding.layoutLaporanContent.tvStatusMenungguCount.text = "1"
        
        // Show Filled Content for Menunggu Verifikasi
        binding.layoutLaporanContent.tvEmptyLaporan.visibility = View.GONE
        binding.layoutLaporanContent.layoutFilledLaporan.visibility = View.VISIBLE
    }
}