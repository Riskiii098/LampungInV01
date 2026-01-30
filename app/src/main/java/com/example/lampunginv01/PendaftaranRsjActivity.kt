package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityPendaftaranRsjBinding

class PendaftaranRsjActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPendaftaranRsjBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendaftaranRsjBinding.inflate(layoutInflater)
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

        binding.btnDaftar.setOnClickListener {
            startActivity(Intent(this, BiodataDiriActivity::class.java))
        }
    }
}
