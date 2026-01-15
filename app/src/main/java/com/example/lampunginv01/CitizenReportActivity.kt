package com.example.lampunginv01

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CitizenReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citizen_report)

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        setupFilters()
        setupTextFormatting()
    }

    private fun setupFilters() {
        val btnDisekitarmu = findViewById<TextView>(R.id.btn_disekitarmu)
        val btnTerbaru = findViewById<TextView>(R.id.btn_terbaru)

        btnDisekitarmu.setOnClickListener {
            // Aktif
            btnDisekitarmu.setBackgroundResource(R.drawable.bg_black_rounded_50)
            btnDisekitarmu.setTextColor(Color.WHITE)
            // Non-aktif
            btnTerbaru.setBackgroundResource(R.drawable.bg_gray_rounded_50)
            btnTerbaru.setTextColor(Color.BLACK)
        }

        btnTerbaru.setOnClickListener {
            // Aktif
            btnTerbaru.setBackgroundResource(R.drawable.bg_black_rounded_50)
            btnTerbaru.setTextColor(Color.WHITE)
            // Non-aktif
            btnDisekitarmu.setBackgroundResource(R.drawable.bg_gray_rounded_50)
            btnDisekitarmu.setTextColor(Color.BLACK)
        }
    }

    private fun setupTextFormatting() {
        // Bold Ratu's Category
        val tvKategori = findViewById<TextView>(R.id.tv_kategori)
        val fullText1 = "Kategori Kerusakan Infrastruktur"
        val spannable1 = SpannableString(fullText1)
        spannable1.setSpan(
            StyleSpan(android.graphics.Typeface.BOLD),
            9, fullText1.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvKategori.text = spannable1

        // Bold Budi's Category
        val tvKategori2 = findViewById<TextView>(R.id.tv_kategori_2)
        val fullText2 = "Kategori Lingkungan"
        val spannable2 = SpannableString(fullText2)
        spannable2.setSpan(
            StyleSpan(android.graphics.Typeface.BOLD),
            9, fullText2.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvKategori2.text = spannable2
    }
}