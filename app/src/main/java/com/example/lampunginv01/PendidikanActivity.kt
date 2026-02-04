package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityPendidikanBinding

class PendidikanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPendidikanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendidikanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        setupActions()
    }

    private fun setupActions() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvLihatDetailBeasiswa.setOnClickListener {
            val intent = Intent(this, BeasiswaDetailActivity::class.java)
            startActivity(intent)
        }

        binding.cardBeasiswa1.setOnClickListener {
            val dialog = BeasiswaDetailDialog()
            dialog.show(supportFragmentManager, "BeasiswaDetailDialog")
        }
        binding.cardBeasiswa2.setOnClickListener {
            Toast.makeText(this, "Beasiswa LPDP Dipilih", Toast.LENGTH_SHORT).show()
        }
        binding.cardPelatihan1.setOnClickListener {
            Toast.makeText(this, "Pelatihan Excel Dipilih", Toast.LENGTH_SHORT).show()
        }
        binding.cardPelatihan2.setOnClickListener {
            Toast.makeText(this, "Pelatihan Excel Dipilih", Toast.LENGTH_SHORT).show()
        }

        // setupVideoList() - Removed as we now use static layout
    }

    /*
    private fun setupVideoList() {
        val videoList = listOf(
            VideoModel("Judul Video 1"),
            VideoModel("Judul Video 2"),
            VideoModel("Judul Video 3")
        )

        val adapter = VideoAdapter(videoList) { video ->
            Toast.makeText(this, "Tonton: ${video.title}", Toast.LENGTH_SHORT).show()
        }

        binding.rvVideos.adapter = adapter
    }
    */
}