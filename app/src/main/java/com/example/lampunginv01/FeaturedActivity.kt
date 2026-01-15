package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class FeaturedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_featured)

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val btnBuatLaporan = findViewById<MaterialCardView>(R.id.btn_buat_laporan)
        btnBuatLaporan.setOnClickListener {
            val intent = Intent(this, MakeReportActivity::class.java)
            startActivity(intent)
            finish()
        }

        val tvLihatSemua = findViewById<TextView>(R.id.tv_lihat_semua)
        tvLihatSemua.setOnClickListener {
            val intent = Intent(this, CitizenReportActivity::class.java)
            startActivity(intent)
        }

        setupInfoCards()
    }

    private fun setupInfoCards() {
        val rvInfoCards = findViewById<RecyclerView>(R.id.rv_info_cards)
        
        val cards = listOf(
            InfoCard(
                "Laporan hanya untuk masalah di Lampung",
                "Agar laporan kamu disetujui, pastikan laporan kamu hanya untuk di wilayah Lampung."
            ),
            InfoCard(
                "Lokasi otomatis berdasarkan foto/video yang diambil",
                "Tetapi, kamu dapat tetap memilih opsi menulis alamat sendiri ya."
            ),
            InfoCard(
                "Satu laporan, untuk satu masalah.",
                "Agar proses laporan lebih mudah, pastikan laporan tidak memiliki dua permasalahan."
            )
        )

        val adapter = InfoCardAdapter(cards)
        rvInfoCards.adapter = adapter
        
        // Setup LayoutManager Horizontal
        rvInfoCards.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        
        // Tambahkan SnapHelper agar kartu berhenti pas di tengah saat digeser
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvInfoCards)
    }
}