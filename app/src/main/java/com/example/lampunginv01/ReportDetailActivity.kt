package com.example.lampunginv01

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReportDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_report_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }

        val etReportDetail = findViewById<android.widget.EditText>(R.id.et_report_detail)
        val tvCharCount = findViewById<android.widget.TextView>(R.id.tv_char_count)

        // Logic Switch Panduan
        val switchGuide = findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.switch_guide)
        val layoutInstructions = findViewById<android.view.View>(R.id.layout_instructions)

        // Set state awal
        layoutInstructions.visibility = if (switchGuide.isChecked) android.view.View.VISIBLE else android.view.View.GONE

        // Listener switch
        switchGuide.setOnCheckedChangeListener { _, isChecked ->
            layoutInstructions.visibility = if (isChecked) android.view.View.VISIBLE else android.view.View.GONE
        }

        etReportDetail.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentLength = s?.length ?: 0
                tvCharCount.text = "$currentLength/2000"
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        // Setup Recommendation Items
        val recommendations = listOf(
            "Terjadi kerusakan pada...",
            "Terdapat pelanggaran di...",
            "Mohon segera tindak lanjuti",
            "Kejadian tidak mengenakkan...",
            "Menurut saya..."
        )

        val itemIds = listOf(
            R.id.item_rec_1,
            R.id.item_rec_2,
            R.id.item_rec_3,
            R.id.item_rec_4,
            R.id.item_rec_5
        )

        itemIds.forEachIndexed { index, id ->
            val textView = findViewById<android.widget.TextView>(id)
            val text = recommendations[index]
            textView.text = text
            
            textView.setOnClickListener {
                etReportDetail.append("$text ")
            }
        }

        findViewById<android.view.View>(R.id.btn_next).setOnClickListener {
            val intent = android.content.Intent(this, ReviewReportActivity::class.java)
            startActivity(intent)
        }
    }
}