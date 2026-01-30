package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityPilihLayananBinding

class PilihLayananActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPilihLayananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihLayananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        setupUI()
        setupActions()
    }

    private fun setupUI() {
        val infoText = "Jika tidak ada juknis yang mengharuskan mengambil jenis paket tes narkoba maka disarankan memilih <b>tes narkoba dengan 6 parameter.</b>"
        binding.tvInfo.text = Html.fromHtml(infoText, Html.FROM_HTML_MODE_COMPACT)
    }

    private fun setupActions() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSelanjutnya.setOnClickListener {
            startActivity(Intent(this, PaymentMethodRsjActivity::class.java))
        }
    }
}
