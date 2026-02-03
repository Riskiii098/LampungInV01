package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityRiwayatPendaftaranBinding

class RiwayatPendaftaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatPendaftaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPendaftaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            if (intent.getBooleanExtra("FROM_RECEIPT", false)) {
                val homeIntent = Intent(this, MainActivity::class.java)
                homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(homeIntent)
                finish()
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        binding.tvDetail.setOnClickListener {
            val intent = Intent(this, PaymentReceiptRsjActivity::class.java)
            startActivity(intent)
        }
    }
}
