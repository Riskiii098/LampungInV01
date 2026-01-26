package com.example.lampunginv01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityKesehatanBinding

class KesehatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKesehatanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKesehatanBinding.inflate(layoutInflater)
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

        binding.cardKesehatan1.setOnClickListener {
            Toast.makeText(this, "Menu Fasilitas Kesehatan Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardRekomendasi.setOnClickListener {
            Toast.makeText(this, "Menu Konsultasi Online Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardKesehatan2.setOnClickListener {
            Toast.makeText(this, "Menu Dokter Jaga Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardKesehatan3.setOnClickListener {
            Toast.makeText(this, "Menu Ketersediaan Kamar Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardKesehatan4.setOnClickListener {
            Toast.makeText(this, "Menu Apotek Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardKesehatan5.setOnClickListener {
            Toast.makeText(this, "Menu Donor Darah Dipilih", Toast.LENGTH_SHORT).show()
        }
    }
}