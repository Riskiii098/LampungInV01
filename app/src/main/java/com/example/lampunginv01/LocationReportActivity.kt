package com.example.lampunginv01

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LocationReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location_report)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }

        // Logic Switch Detail Lokasi
        val switchLocation = findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.switch_location)
        val layoutAutoAddress = findViewById<android.widget.LinearLayout>(R.id.layout_auto_address)

        // Set state awal
        layoutAutoAddress.visibility = if (switchLocation.isChecked) android.view.View.VISIBLE else android.view.View.GONE

        // Listener switch
        switchLocation.setOnCheckedChangeListener { _, isChecked ->
            layoutAutoAddress.visibility = if (isChecked) android.view.View.VISIBLE else android.view.View.GONE
        }

        findViewById<android.view.View>(R.id.btn_next).setOnClickListener {
            val intent = android.content.Intent(this, ReportDetailActivity::class.java)
            startActivity(intent)
        }
    }
}