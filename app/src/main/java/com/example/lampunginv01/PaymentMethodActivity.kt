package com.example.lampunginv01

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityPaymentMethodBinding

class PaymentMethodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentMethodBinding
    private var isQrisSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentMethodBinding.inflate(layoutInflater)
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

        val paymentOptions = mapOf(
            binding.cardQris to binding.radioInnerQris,
            binding.cardShopeepay to binding.radioInnerShopeepay,
            binding.cardGopay to binding.radioInnerGopay,
            binding.cardBca to binding.radioInnerBca,
            binding.cardBri to binding.radioInnerBri,
            binding.cardBankLampung to binding.radioInnerBankLampung,
            
            // Allow clicking the radio button container directly as well
            binding.radioQris to binding.radioInnerQris,
            binding.radioShopeepay to binding.radioInnerShopeepay,
            binding.radioGopay to binding.radioInnerGopay,
            binding.radioBca to binding.radioInnerBca,
            binding.radioBri to binding.radioInnerBri,
            binding.radioBankLampung to binding.radioInnerBankLampung
        )

        for ((clickable, indicator) in paymentOptions) {
            clickable.setOnClickListener {
                selectPaymentMethod(indicator)
            }
        }
    }

    private fun selectPaymentMethod(selectedIndicator: View) {
        // Hide all indicators first
        binding.radioInnerQris.visibility = View.GONE
        binding.radioInnerShopeepay.visibility = View.GONE
        binding.radioInnerGopay.visibility = View.GONE
        binding.radioInnerBca.visibility = View.GONE
        binding.radioInnerBri.visibility = View.GONE
        binding.radioInnerBankLampung.visibility = View.GONE

        // Show the selected one
        selectedIndicator.visibility = View.VISIBLE
    }
}