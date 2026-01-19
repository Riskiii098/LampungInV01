package com.example.lampunginv01

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityMenuSpesialBinding

class MenuSpesialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuSpesialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuSpesialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle Window Insets (Status Bar)
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupVideoPlayer()
        
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupVideoPlayer() {
        val videoPath = "android.resource://" + packageName + "/" + R.raw.video_spesial
        
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        binding.videoView.setVideoURI(Uri.parse(videoPath))
        
        binding.videoView.setOnPreparedListener { mp ->
            binding.progressBar.visibility = View.GONE
            mp.start()
        }

        binding.videoView.setOnErrorListener { mp, what, extra ->
            binding.progressBar.visibility = View.GONE
            false
        }
    }
}