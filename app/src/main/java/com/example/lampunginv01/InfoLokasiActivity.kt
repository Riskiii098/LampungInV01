package com.example.lampunginv01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityInfoLokasiBinding

class InfoLokasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoLokasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoLokasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnWifi.setOnClickListener { startActivity(android.content.Intent(this, WifiActivity::class.java)) }
        binding.btnRptra.setOnClickListener { startActivity(android.content.Intent(this, RptraActivity::class.java)) }
        binding.btnRth.setOnClickListener { startActivity(android.content.Intent(this, RthActivity::class.java)) }
        binding.btnBank.setOnClickListener { startActivity(android.content.Intent(this, BankActivity::class.java)) }
        binding.btnPerpustakaan.setOnClickListener { startActivity(android.content.Intent(this, PerpustakaanActivity::class.java)) }
        binding.btnOlahraga.setOnClickListener { startActivity(android.content.Intent(this, SaranaOlahragaActivity::class.java)) }
        binding.btnBus.setOnClickListener { startActivity(android.content.Intent(this, BusStopActivity::class.java)) }
        binding.btnStasiun.setOnClickListener { startActivity(android.content.Intent(this, StasiunActivity::class.java)) }
        binding.btnTaman.setOnClickListener { startActivity(android.content.Intent(this, TamanRekreasiActivity::class.java)) }
        binding.btnKuliner.setOnClickListener { startActivity(android.content.Intent(this, KulinerActivity::class.java)) }
        binding.btnBelanja.setOnClickListener { startActivity(android.content.Intent(this, BelanjaActivity::class.java)) }
        binding.btnAlam.setOnClickListener { startActivity(android.content.Intent(this, DestinasiAlamActivity::class.java)) }
        binding.btnBudaya.setOnClickListener { startActivity(android.content.Intent(this, KebudayaanActivity::class.java)) }
        binding.btnReligi.setOnClickListener { startActivity(android.content.Intent(this, ReligiActivity::class.java)) }
        binding.btnHotel.setOnClickListener { startActivity(android.content.Intent(this, HotelActivity::class.java)) }
    }
}