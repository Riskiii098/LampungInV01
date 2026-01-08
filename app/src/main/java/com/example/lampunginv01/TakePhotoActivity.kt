package com.example.lampunginv01

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TakePhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_take_photo)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvPrivacy = findViewById<TextView>(R.id.tv_privacy_policy)
        tvPrivacy.paintFlags = tvPrivacy.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        // Views
        val btnAllow = findViewById<View>(R.id.btn_allow)
        val btnShutter = findViewById<View>(R.id.btn_shutter)
        val btnRetake = findViewById<View>(R.id.btn_retake)
        val btnUsePhoto = findViewById<View>(R.id.btn_use_photo)
        
        val layoutPermission = findViewById<View>(R.id.layout_permission)
        val layoutCameraUi = findViewById<View>(R.id.layout_camera_ui)
        val layoutReviewUi = findViewById<View>(R.id.layout_review_ui)
        
        val cameraPreviewContainer = findViewById<View>(R.id.camera_preview_container)
        val bottomContainer = findViewById<View>(R.id.bottom_container)
        val ivCapturedPhoto = findViewById<ImageView>(R.id.iv_captured_photo)

        // 1. Logic Klik Izinkan -> Masuk Mode Kamera (State 2)
        btnAllow.setOnClickListener {
            layoutPermission.visibility = View.GONE
            layoutCameraUi.visibility = View.VISIBLE
            
            // Ubah Background Preview jadi agak gelap (simulasi kamera aktif)
            cameraPreviewContainer.setBackgroundColor(Color.parseColor("#333333"))

            // Ubah Ukuran Layout secara Dinamis
            setCameraLayoutWeights(cameraPreviewContainer, bottomContainer)
        }

        // 2. Logic Klik Shutter -> Masuk Mode Tinjau (State 3)
        btnShutter.setOnClickListener {
            layoutCameraUi.visibility = View.GONE
            layoutReviewUi.visibility = View.VISIBLE
            
            // Foto tidak ditampilkan sesuai permintaan
            ivCapturedPhoto.visibility = View.GONE
            
            // Ubah Judul
            tvTitle.text = "Tinjau Foto"
        }

        // 3. Logic Klik Ulangi -> Kembali ke Mode Kamera (State 2)
        btnRetake.setOnClickListener {
            layoutReviewUi.visibility = View.GONE
            layoutCameraUi.visibility = View.VISIBLE
            ivCapturedPhoto.visibility = View.GONE
            
            tvTitle.text = "Ambil Foto Laporan"
        }

        // 4. Logic Klik Gunakan -> Lanjut ke LocationReportActivity
        btnUsePhoto.setOnClickListener {
            val intent = android.content.Intent(this, LocationReportActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setCameraLayoutWeights(previewContainer: View, bottomContainer: View) {
        // Memperbesar area preview (weight 3.7 -> 4.5)
        // Memperkecil area bawah (weight 2.6 -> 1.8)
        val previewParams = previewContainer.layoutParams as LinearLayout.LayoutParams
        previewParams.weight = 4.5f
        previewContainer.layoutParams = previewParams

        val bottomParams = bottomContainer.layoutParams as LinearLayout.LayoutParams
        bottomParams.weight = 1.8f
        bottomContainer.layoutParams = bottomParams
    }
}