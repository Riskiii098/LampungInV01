package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityKarirUsahaBinding

class KarirUsahaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKarirUsahaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKarirUsahaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        setupActions()
    }

    private fun setupActions() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.cardKarir1.setOnClickListener {
            val intent = Intent(this, LowonganKerjaActivity::class.java)
            startActivity(intent)
        }

        binding.cardKarir2.setOnClickListener {
            Toast.makeText(this, "Fitur Perizinan belum tersedia", Toast.LENGTH_SHORT).show()
        }

        binding.cardKarir3.setOnClickListener {
            Toast.makeText(this, "Fitur Pelatihan Kerja belum tersedia", Toast.LENGTH_SHORT).show()
        }

        binding.cardKarir4.setOnClickListener {
            Toast.makeText(this, "Fitur Info Magang belum tersedia", Toast.LENGTH_SHORT).show()
        }
    }
}