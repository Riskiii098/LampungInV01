package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class ReportSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_report_success)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi View
        val ivBackground = findViewById<ImageView>(R.id.iv_background_yellow)
        val ivSuccessIcon = findViewById<ImageView>(R.id.iv_success_icon)
        val tvTitle = findViewById<TextView>(R.id.tv_title_success)
        val tvSubtitle = findViewById<TextView>(R.id.tv_subtitle_success)
        val btnFinish = findViewById<MaterialButton>(R.id.btn_finish)
        val btnCheckStatus = findViewById<MaterialButton>(R.id.btn_check_status)
        val ivClose = findViewById<ImageView>(R.id.iv_close)

        // Format Teks Subtitle (Bold di 'halaman aktivitas')
        val subtitleText = "Cek progress laporan kamu di <b>halaman aktivitas</b>"
        tvSubtitle.text = android.text.Html.fromHtml(subtitleText, android.text.Html.FROM_HTML_MODE_LEGACY)

        // --- SETUP AWAL ANIMASI ---
        
        // 1. Sembunyikan Ikon & Teks dulu
        ivSuccessIcon.scaleX = 0f
        ivSuccessIcon.scaleY = 0f
        tvTitle.alpha = 0f
        tvSubtitle.alpha = 0f
        btnCheckStatus.alpha = 0f
        btnFinish.alpha = 0f
        ivClose.alpha = 0f
        
        // 2. Siapkan Background di atas layar
        ivBackground.translationY = -1000f 

        // --- JALANKAN URUTAN ANIMASI ---
        ivBackground.post {
            // Pastikan posisi awal background tepat di atas viewnya sendiri
            ivBackground.translationY = -ivBackground.height.toFloat()

            // TAHAP 1: Background Meluncur Turun
            ivBackground.animate()
                .translationY(0f)
                .setDuration(800)
                .setInterpolator(android.view.animation.DecelerateInterpolator())
                .withEndAction {
                    
                    // TAHAP 2: Ikon Sukses Muncul
                    ivSuccessIcon.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(500)
                        .setInterpolator(OvershootInterpolator(1.5f))
                        .start()

                    // TAHAP 3: Teks & Tombol Muncul
                    tvTitle.animate().alpha(1f).setDuration(500).setStartDelay(200).start()
                    tvSubtitle.animate().alpha(1f).setDuration(500).setStartDelay(300).start()
                    ivClose.animate().alpha(1f).setDuration(500).setStartDelay(300).start()
                    
                    // Tombol muncul dari bawah sedikit
                    btnCheckStatus.translationY = 50f
                    btnFinish.translationY = 50f
                    
                    btnCheckStatus.animate().alpha(1f).translationY(0f).setDuration(500).setStartDelay(400).start()
                    btnFinish.animate().alpha(1f).translationY(0f).setDuration(500).setStartDelay(500).start()
                }
                .start()
        }

        // Fungsi kembali ke Home
        fun goHome() {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Tombol Cek Status
        btnCheckStatus.setOnClickListener {
            val intent = Intent(this, ActivitySimulatedHistory::class.java)
            startActivity(intent)
            finish()
        }

        // Tombol Kembali
        btnFinish.setOnClickListener {
            goHome()
        }

        // Tombol Close (X)
        ivClose.setOnClickListener {
            val intent = Intent(this, ActivitySimulatedHistory::class.java)
            startActivity(intent)
            finish()
        }
    }
}