package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityDaruratBinding

class DaruratActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaruratBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaruratBinding.inflate(layoutInflater)
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

        binding.cardDarurat1.setOnClickListener {
            startActivity(Intent(this, KontakDaruratActivity::class.java))
        }

        binding.cardDarurat2.setOnClickListener {
            startActivity(Intent(this, InfoAmbulansActivity::class.java))
        }

        binding.cardDarurat3.setOnClickListener {
            Toast.makeText(this, "Menu Posko Keamanan Dipilih", Toast.LENGTH_SHORT).show()
        }
    }
}