package com.example.lampunginv01

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class PajakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pajak)

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.Button>(R.id.btn_riwayat_pajak).setOnClickListener {
            val intent = android.content.Intent(this, PaymentHistoryActivity::class.java)
            startActivity(intent)
        }
        
        setupRecyclerView()

        // Handle window insets if necessary (optional but good practice)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        val rvServices = findViewById<RecyclerView>(R.id.rv_pajak_services)
        
        val serviceList = listOf(
            PajakServiceModel(1, "Pajak Kendaraan Bermotor (PKB)", "Cek & Bayar Pajak", R.drawable.pajak1),
            PajakServiceModel(2, "Pajak Bumi & Bangunan (PBB)", "Cek & Bayar Pajak", R.drawable.pajak2),
            PajakServiceModel(3, "Pajak Daerah", "Cek & Bayar Pajak", R.drawable.pajak3)
        )

        val adapter = PajakServiceAdapter(serviceList) { item ->
            if (item.id == 1) {
                showCekPajakBottomSheet()
            } else {
                Toast.makeText(this, "Fitur ini belum tersedia untuk ${item.title}", Toast.LENGTH_SHORT).show()
            }
        }

        rvServices.layoutManager = LinearLayoutManager(this)
        rvServices.adapter = adapter
    }

    private fun showCekPajakBottomSheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_cek_pajak, null)
        dialog.setContentView(view)

        view.findViewById<android.view.View>(R.id.btn_close).setOnClickListener {
            dialog.dismiss()
        }

        view.findViewById<android.view.View>(R.id.btn_cek_pajak_submit).setOnClickListener {
            dialog.dismiss()
            val intent = android.content.Intent(this, VehicleInfoActivity::class.java)
            startActivity(intent)
        }

        dialog.show()
    }
}
