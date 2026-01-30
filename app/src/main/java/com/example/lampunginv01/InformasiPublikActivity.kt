package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityInformasiPublikBinding

class InformasiPublikActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformasiPublikBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiPublikBinding.inflate(layoutInflater)
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

        binding.cardInfo1.setOnClickListener {
            startActivity(Intent(this, BeritaActivity::class.java))
        }

        binding.cardInfo2.setOnClickListener {
            Toast.makeText(this, "Menu JDIH Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardInfo3.setOnClickListener {
            Toast.makeText(this, "Menu PPID Dipilih", Toast.LENGTH_SHORT).show()
        }

        binding.cardInfo4.setOnClickListener {
            Toast.makeText(this, "Menu Open Data Dipilih", Toast.LENGTH_SHORT).show()
        }
    }
}