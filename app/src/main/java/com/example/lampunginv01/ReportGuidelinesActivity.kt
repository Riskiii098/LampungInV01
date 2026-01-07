package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityReportGuidelinesBinding

class ReportGuidelinesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportGuidelinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityReportGuidelinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top + 40, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnContinue.setOnClickListener {
            // if (binding.cbDontShowAgain.isChecked) {
            //     val sharedPref = getSharedPreferences("LampungInPrefs", MODE_PRIVATE)
            //     with(sharedPref.edit()) {
            //         putBoolean("skip_report_guidelines", true)
            //         apply()
            //     }
            // }
            
            // val intent = Intent(this, MainActivity::class.java)
            // intent.putExtra("OPEN_REPORT", true)
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            // startActivity(intent)
            // finish()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}