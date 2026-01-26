package com.example.lampunginv01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityKependudukanBinding

class KependudukanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKependudukanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKependudukanBinding.inflate(layoutInflater)
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

        // Kab. Lampung Selatan
        binding.innerItem1.setOnClickListener {
            openUrl("https://e-dukcapil.lampungselatankab.go.id/pengajuan/dafduk/kk/add")
        }

        // Kota Metro
        binding.innerItemLainnya1.setOnClickListener {
            openUrl("https://layanan-online.dukcapil.metrokota.go.id/")
        }

        // Kab. Lampung Timur
        binding.innerItemLainnya2.setOnClickListener {
            openUrl("https://silamtimberjaya.lampungtimurkab.go.id/pengajuan/capil/akta-lahir/add")
        }

        // Kab. Tulang Bawang Barat
        binding.innerItemLainnya3.setOnClickListener {
            openUrl("https://e-smile.tubaba.go.id/pengajuan/dafduk/kk/add")
        }
    }

    private fun openUrl(url: String) {
        try {
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse(url)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Tidak dapat membuka link: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}