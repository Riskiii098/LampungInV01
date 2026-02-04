package com.example.lampunginv01

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            if (intent.getBooleanExtra("FROM_RECEIPT", false)) {
                val homeIntent = android.content.Intent(this, MainActivity::class.java)
                homeIntent.flags = android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK or android.content.Intent.FLAG_ACTIVITY_NEW_TASK
                val pajakIntent = android.content.Intent(this, PajakActivity::class.java)
                startActivities(arrayOf(homeIntent, pajakIntent))
                finish()
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        findViewById<android.widget.TextView>(R.id.tv_detail).setOnClickListener {
            val intent = android.content.Intent(this, PaymentReceiptActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}