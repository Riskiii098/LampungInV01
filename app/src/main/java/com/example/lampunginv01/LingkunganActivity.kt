package com.example.lampunginv01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityLingkunganBinding

class LingkunganActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLingkunganBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLingkunganBinding.inflate(layoutInflater)
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

        binding.cardLingkungan1.setOnClickListener {
            Toast.makeText(this, "Menu Pantau Banjir Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardLingkungan2.setOnClickListener {
            Toast.makeText(this, "Menu Peringatan Dini Cuaca Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardLingkungan3.setOnClickListener {
            Toast.makeText(this, "Menu Perizinan Tata Ruang (Simtaru) Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardLingkungan4.setOnClickListener {
            Toast.makeText(this, "Menu Dinas Energi dan Sumber Daya (ESDM) Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardLingkungan5.setOnClickListener {
            Toast.makeText(this, "Menu Dinas Lingkungan Hidup (DLH) Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardLingkungan6.setOnClickListener {
            Toast.makeText(this, "Menu SIH3 Dipilih", Toast.LENGTH_SHORT).show()
        }
    }
}