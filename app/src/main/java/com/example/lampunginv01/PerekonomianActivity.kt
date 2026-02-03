package com.example.lampunginv01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityPerekonomianBinding

class PerekonomianActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerekonomianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerekonomianBinding.inflate(layoutInflater)
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

        binding.cardEkonomi1.setOnClickListener {
            startActivity(android.content.Intent(this, PajakActivity::class.java))
        }

        binding.cardEkonomi2.setOnClickListener {
            startActivity(android.content.Intent(this, CekHargaPanganActivity::class.java))
        }
    }
}