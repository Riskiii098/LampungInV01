package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityPaymentMethodRsjBinding

class PaymentMethodRsjActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentMethodRsjBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentMethodRsjBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Map card/container to the inner radio indicator view
        val paymentOptions = mapOf(
            binding.cardQris to binding.radioInnerQris,
            binding.radioQris to binding.radioInnerQris,
            binding.cardTellerBankLampung to binding.radioInnerTellerBankLampung,
            binding.radioTellerBankLampung to binding.radioInnerTellerBankLampung
        )

        for ((clickable, indicator) in paymentOptions) {
            clickable.setOnClickListener {
                selectPaymentMethod(indicator)
            }
        }

        binding.btnConfirmPay.setOnClickListener {
            if (binding.radioInnerQris.visibility == View.VISIBLE) {
                 // Navigate to QRIS code with specific price
                 val intent = Intent(this, KodeQrisActivity::class.java)
                 intent.putExtra("TOTAL_PRICE", "Rp 260.000")
                 intent.putExtra("RECEIPT_TYPE", "RSJ")
                 startActivity(intent)
            } else if (binding.radioInnerTellerBankLampung.visibility == View.VISIBLE) {
                 // Navigate to instruction for Teller or Success
                 Toast.makeText(this, "Silakan lakukan pembayaran di Teller Bank Lampung", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Pilih metode pembayaran terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectPaymentMethod(selectedIndicator: View) {
        // Hide all indicators
        binding.radioInnerQris.visibility = View.GONE
        binding.radioInnerTellerBankLampung.visibility = View.GONE

        // Show selected
        selectedIndicator.visibility = View.VISIBLE
    }
}
