package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityRegisterPhoneBinding

class RegisterPhoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, RegisterTermsActivity::class.java))
        }

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, RegisterTermsActivity::class.java))
        }
    }
}