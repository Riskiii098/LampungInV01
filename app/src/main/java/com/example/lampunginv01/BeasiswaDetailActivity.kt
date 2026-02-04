package com.example.lampunginv01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityBeasiswaDetailBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BeasiswaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeasiswaDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeasiswaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.cardBeasiswa1.setOnClickListener {
            val dialog = BeasiswaDetailDialog()
            dialog.show(supportFragmentManager, "BeasiswaDetailDialog")
        }
    }
}