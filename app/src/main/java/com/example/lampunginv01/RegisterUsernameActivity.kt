package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityRegisterUsernameBinding

class RegisterUsernameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUsernameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUsernameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, RegisterEmailActivity::class.java))
        }
    }
}