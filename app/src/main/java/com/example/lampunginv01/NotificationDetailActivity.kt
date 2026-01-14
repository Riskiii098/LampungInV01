package com.example.lampunginv01

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityNotificationDetailBinding

class NotificationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        // Paragraf 1 dengan bagian yang Bold
        val fullText = "Badan Penanggulangan Bencana Daerah (BPBD) Provinsi Lampung menyampaikan peringatan dini banjir kepada masyarakat menyusul meningkatnya curah hujan dalam beberapa hari terakhir. Kondisi tersebut berpotensi menyebabkan genangan dan luapan sungai di wilayah rawan banjir."
        val boldPart = "peringatan dini banjir kepada masyarakat menyusul meningkatnya curah hujan dalam beberapa hari terakhir"
        
        val spannableString = SpannableString(fullText)
        val start = fullText.indexOf(boldPart)
        if (start != -1) {
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                start,
                start + boldPart.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        
        binding.tvDescription1.text = spannableString
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}