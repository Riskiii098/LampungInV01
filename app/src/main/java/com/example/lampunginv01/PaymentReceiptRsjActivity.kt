package com.example.lampunginv01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityPaymentReceiptRsjBinding

class PaymentReceiptRsjActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentReceiptRsjBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentReceiptRsjBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnClose.setOnClickListener {
            val intent = android.content.Intent(this, RiwayatPendaftaranActivity::class.java)
            intent.putExtra("FROM_RECEIPT", true)
            startActivity(intent)
            finish()
        }

        binding.btnHome.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java)
            intent.flags = android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        binding.btnDownload.setOnClickListener {
            Toast.makeText(this, "Sedang mengunduh kwitansi...", Toast.LENGTH_SHORT).show()
        }
    }
}
