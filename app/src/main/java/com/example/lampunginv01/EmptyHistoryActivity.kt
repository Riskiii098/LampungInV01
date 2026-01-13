package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityEmptyHistoryBinding

class EmptyHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmptyHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmptyHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnBuatLaporan.setOnClickListener {
            // Usually goes to MakeReportActivity or similar
            val intent = Intent(this, MakeReportActivity::class.java)
            startActivity(intent)
        }
    }
}