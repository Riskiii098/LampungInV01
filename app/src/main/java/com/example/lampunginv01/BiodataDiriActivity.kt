package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityBiodataDiriBinding

class BiodataDiriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiodataDiriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiodataDiriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        setupSpinner()

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSelanjutnya.setOnClickListener {
            startActivity(Intent(this, AlamatActivity::class.java))
        }
    }

    private fun setupSpinner() {
        val genderOptions = arrayOf("Pilih Jenis Kelamin", "Laki-Laki", "Perempuan")
        val adapter = ArrayAdapter(this, R.layout.spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerJenisKelamin.adapter = adapter
    }
}
