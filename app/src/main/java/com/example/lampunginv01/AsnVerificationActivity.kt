package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityAsnVerificationBinding

class AsnVerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsnVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsnVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.rbNip.setOnClickListener {
            binding.rbNip.isChecked = true
            binding.rbEmail.isChecked = false
        }

        binding.cardNip.setOnClickListener {
            binding.rbNip.isChecked = true
            binding.rbEmail.isChecked = false
        }

        binding.rbEmail.setOnClickListener {
            binding.rbEmail.isChecked = true
            binding.rbNip.isChecked = false
        }

        binding.cardEmail.setOnClickListener {
            binding.rbEmail.isChecked = true
            binding.rbNip.isChecked = false
        }

        binding.btnLanjutkan.setOnClickListener {
            if (binding.rbEmail.isChecked) {
                val intent = Intent(this, AsnEmailVerificationActivity::class.java)
                startActivity(intent)
            } else if (binding.rbNip.isChecked) {
                Toast.makeText(this, "Melanjutkan dengan NIP", Toast.LENGTH_SHORT).show()
                // Navigasi untuk NIP bisa ditambahkan nanti
            } else {
                Toast.makeText(this, "Pilih salah satu opsi verifikasi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}