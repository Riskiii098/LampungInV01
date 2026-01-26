package com.example.lampunginv01

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

        binding.cardBeasiswa1.setOnClickListener {
            Toast.makeText(this, "Beasiswa Unggulan Dipilih", Toast.LENGTH_SHORT).show()
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

        setupVideoList()
    }

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
}