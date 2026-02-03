package com.example.lampunginv01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivitySosialKeagamaanBinding

class SosialKeagamaanActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySosialKeagamaanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySosialKeagamaanBinding.inflate(layoutInflater)
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

        binding.cardSosial1.setOnClickListener {
            Toast.makeText(this, "Menu Informasi dan Cek Bansos Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardSosial2.setOnClickListener {
            Toast.makeText(this, "Menu Pembayaran Zakat (Baznas) Dipilih", Toast.LENGTH_SHORT).show()
        }
    }
}