package com.example.lampunginv01

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityProgressLaporanBinding

class ProgressLaporanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgressLaporanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }

        setupAgencyText()
    }

    private fun setupAgencyText() {
        val prefix = "Laporan di teruskan ke "
        val agency = "Dinas Bina Marga dan Bina Konstruksi (BMBK) Provinsi Lampung"
        val fullText = prefix + agency
        
        val spannable = SpannableStringBuilder(fullText)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            prefix.length,
            fullText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        
        binding.desc3.text = spannable
    }
}