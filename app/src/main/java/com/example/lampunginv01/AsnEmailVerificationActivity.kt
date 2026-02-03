package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityAsnEmailVerificationBinding

class AsnEmailVerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsnEmailVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsnEmailVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVerifikasi.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isNotEmpty()) {
                val intent = Intent(this, AsnOtpVerificationActivity::class.java)
                intent.putExtra("EMAIL", email)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Silakan masukkan email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}