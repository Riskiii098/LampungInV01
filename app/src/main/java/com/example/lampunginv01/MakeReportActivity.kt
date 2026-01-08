package com.example.lampunginv01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView

class MakeReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_make_report)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_content) ?: findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<ImageView>(R.id.iv_back)
        btnBack.setOnClickListener {
            finish()
        }

        val rvCategories = findViewById<RecyclerView>(R.id.rv_categories)
        val categories = listOf("Keadaan Darurat", "Kerusakan Infrastruktur", "Keluhan Layanan Publik", "Masalah Kesehatan", "Gangguan Lingkungan")
        val adapter = ReportCategoryAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = adapter

        // Setup All Categories (Expandable)
        val rvAllCategories = findViewById<RecyclerView>(R.id.rv_all_categories)
        val allCategories = listOf(
            ExpandableCategoryAdapter.ExpandableItem(
                "Keadaan Darurat",
                "Situasi mendesak (berkaitan dengan keselamatan jiwa, kesehatan, atau keamanan publik), butuh respon cepat."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Masalah Layanan Kesehatan",
                "Keluhan terkait layanan kesehatan seperti fasilitas medis, rumah sakit, BPJS, ketersediaan ambulans, atau kebutuhan donor darah."
            )
        )
        val expandableAdapter = ExpandableCategoryAdapter(allCategories) { categoryName ->
            // Callback ketika item diklik -> Pindah ke halaman Ambil Foto (Langkah 2)
            val intent = android.content.Intent(this, TakePhotoActivity::class.java)
            intent.putExtra("CATEGORY_NAME", categoryName)
            startActivity(intent)
        }
        rvAllCategories.layoutManager = LinearLayoutManager(this)
        rvAllCategories.adapter = expandableAdapter
    }
}