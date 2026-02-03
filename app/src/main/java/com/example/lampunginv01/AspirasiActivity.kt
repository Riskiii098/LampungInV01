package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityAspirasiBinding

class AspirasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAspirasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAspirasiBinding.inflate(layoutInflater)
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

        binding.cardLaporanWarga.setOnClickListener {
            startActivity(Intent(this, MakeReportActivity::class.java))
        }

        binding.cardLaporanAktivitas.setOnClickListener {
            Toast.makeText(this, "Menu Survey Kepuasan Masyarakat Dipilih", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Activity List if needed
        }
    }
}