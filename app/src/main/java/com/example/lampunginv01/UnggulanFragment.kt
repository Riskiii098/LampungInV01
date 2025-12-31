package com.example.lampunginv01

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lampunginv01.databinding.FragmentUnggulanBinding

class UnggulanFragment : Fragment() {
    private var _binding: FragmentUnggulanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnggulanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Administrasi Kependudukan Listeners
        
        // Kab. Lampung Selatan
        binding.btnKabLampungSelatan.setOnClickListener {
            openUrl("https://e-dukcapil.lampungselatankab.go.id/pengajuan/dafduk/kk/add")
        }

        // Kab. Lampung Timur
        binding.btnKabLampungTimur.setOnClickListener {
            openUrl("https://silamtimberjaya.lampungtimurkab.go.id/pengajuan/capil/akta-lahir/add")
        }

        // Kab. Tulang Bawang Barat
        binding.btnKabTulangBawangBarat.setOnClickListener {
            openUrl("https://e-smile.tubaba.go.id/pengajuan/dafduk/kk/add")
        }

        // Kota Metro
        binding.btnKotaMetro.setOnClickListener {
            openUrl("https://layanan-online.dukcapil.metrokota.go.id/")
        }

        // Lainnya - Default (bisa diarahkan ke halaman utama dukcapil provinsi atau daftar lengkap)
        binding.btnLainnyaKependudukan.setOnClickListener {
            // Contoh URL umum, bisa diganti sesuai kebutuhan
            openUrl("https://dukcapil.lampungprov.go.id/") 
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}