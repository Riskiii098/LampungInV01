package com.example.lampunginv01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityRegisterTermsBinding

class RegisterTermsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterTermsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            if (binding.cbAgree.isChecked) {
                // Proceed to registration success
                startActivity(android.content.Intent(this, RegisterSuccessActivity::class.java))
            } else {
                Toast.makeText(this, "Mohon setujui Syarat dan Ketentuan", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSkip.setOnClickListener {
            // Handle skip logic
            finishAffinity() // Or navigate back to login
        }
    }
}