package com.example.lampunginv01

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityDetailRiwayatBinding

class DetailRiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRiwayatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, 0, v.paddingRight, v.paddingBottom) 
            insets
        }

        binding.ivBack.setOnClickListener {
            if (intent.getBooleanExtra("FROM_SUCCESS", false)) {
                val intent = Intent(this, RiwayatLaporanActivity::class.java)
                intent.putExtra("GO_TO_HOME", true)
                startActivity(intent)
                finish()
            } else {
                finish()
            }
        }

        binding.tvSelengkapnya.setOnClickListener {
            val intent = Intent(this, ProgressLaporanActivity::class.java)
            startActivity(intent)
        }

        setupProgressText()
    }

    private fun setupProgressText() {
        // Item 1: Menunggu Verifikasi
        binding.desc1.text = "Laporan dalam proses pengecekan BIRO PEMERINTAHAN"

        // Item 3: Diteruskan ke Instansi
        val prefix3 = "Laporan di teruskan ke "
        val agency3 = "Dinas Bina Marga dan Bina Konstruksi (BMBK) Provinsi Lampung"
        val fullText3 = prefix3 + agency3
        val spannable3 = SpannableStringBuilder(fullText3)
        spannable3.setSpan(
            StyleSpan(Typeface.BOLD),
            prefix3.length,
            fullText3.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.desc3.text = spannable3
    }
}