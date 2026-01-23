package com.example.lampunginv01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityPaymentReceiptBinding

class PaymentReceiptActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}