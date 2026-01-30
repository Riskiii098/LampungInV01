package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityInformasiLainnyaBinding

class InformasiLainnyaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformasiLainnyaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiLainnyaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        setupSpinners()

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSimpan.setOnClickListener {
            startActivity(Intent(this, PilihLayananActivity::class.java))
        }
    }

    private fun setupSpinners() {
        // Golongan Darah
        val bloodOptions = arrayOf("Golongan Darah", "A", "B", "O", "AB")
        val bloodAdapter = ArrayAdapter(this, R.layout.spinner_item, bloodOptions)
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGolonganDarah.adapter = bloodAdapter

        // Status Menikah
        val statusOptions = arrayOf("Status Menikah", "Belum Menikah", "Sudah Menikah", "Janda", "Duda")
        val statusAdapter = ArrayAdapter(this, R.layout.spinner_item, statusOptions)
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStatusMenikah.adapter = statusAdapter

        // Pendidikan
        val eduOptions = arrayOf("Pilih Pendidikan", "TK", "SD", "SMP", "SMA", "SMK", "D1", "D2", "D3", "D4", "S1", "S2", "S3", "Tidak Sekolah")
        val eduAdapter = ArrayAdapter(this, R.layout.spinner_item, eduOptions)
        eduAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPendidikan.adapter = eduAdapter
    }
}
