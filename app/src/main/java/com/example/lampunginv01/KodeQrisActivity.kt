package com.example.lampunginv01

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityKodeQrisBinding

class KodeQrisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKodeQrisBinding
    private var isExpanded = false
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKodeQrisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        startTimer()

        val totalPrice = intent.getStringExtra("TOTAL_PRICE")
        if (totalPrice != null) {
            binding.tvTotalPayment.text = totalPrice
        }
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
            finish()
        }

        binding.layoutInstructionsHeader.setOnClickListener {
            toggleInstructions()
        }

        binding.btnDownloadQris.setOnClickListener {
            Toast.makeText(this, "Mengunduh QRIS...", Toast.LENGTH_SHORT).show()
        }

        binding.tvRefreshStatus.setOnClickListener {
            val intent = android.content.Intent(this, PaymentSuccessActivity::class.java)
            if (getIntent().hasExtra("TOTAL_PRICE")) {
                intent.putExtra("TOTAL_PRICE", getIntent().getStringExtra("TOTAL_PRICE"))
            }
            if (getIntent().hasExtra("RECEIPT_TYPE")) {
                intent.putExtra("RECEIPT_TYPE", getIntent().getStringExtra("RECEIPT_TYPE"))
            }
            startActivity(intent)
            finish() // Optional: Finish Qris activity so back button goes to main menu
        }
    }

    private fun toggleInstructions() {
        if (isExpanded) {
            binding.layoutInstructionsContent.visibility = View.GONE
            binding.ivArrowInstructions.rotation = 0f
        } else {
            binding.layoutInstructionsContent.visibility = View.VISIBLE
            binding.ivArrowInstructions.rotation = 180f
        }
        isExpanded = !isExpanded
    }

    private fun startTimer() {
        // 10 minutes in milliseconds
        val totalTime = 10 * 60 * 1000L 

        timer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                val timeString = String.format("%d Menit : %02d Detik", minutes, seconds)
                binding.tvTimer.text = timeString
            }

            override fun onFinish() {
                binding.tvTimer.text = "0 Menit : 00 Detik"
                Toast.makeText(this@KodeQrisActivity, "Waktu pembayaran habis", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
