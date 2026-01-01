package com.example.lampunginv01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lampunginv01.databinding.FragmentKategoriBinding

class KategoriFragment : Fragment() {
    private var _binding: FragmentKategoriBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGrid()
    }

    private fun setupGrid() {
        // Data Dummy (14 Item: 7 Kiri, 7 Kanan)
        // Semua menggunakan icon Sosial (kt_aspirasi) sesuai permintaan
        val categoryList = listOf(
            MenuModel(1, "Administrasi Kependudukan", R.drawable.kt_aspirasi),
            MenuModel(2, "Kesehatan", R.drawable.kt_aspirasi),
            MenuModel(3, "Fasilitas Umum", R.drawable.kt_aspirasi),
            MenuModel(4, "Darurat & Keamanan", R.drawable.kt_aspirasi),
            MenuModel(5, "Informasi Publik & Data", R.drawable.kt_aspirasi),
            MenuModel(6, "Lingkungan & Kebencanaan", R.drawable.kt_aspirasi),
            MenuModel(7, "Pendidikan & Pengembangan SDM", R.drawable.kt_aspirasi),
            MenuModel(8, "Perizinan & Usaha", R.drawable.kt_aspirasi),
            MenuModel(9, "Perizinan & Usaha", R.drawable.kt_aspirasi),
            MenuModel(10, "Ketenagakerjaan", R.drawable.kt_aspirasi),
            MenuModel(11, "Layanan Sosial & Keagamaan", R.drawable.kt_aspirasi),
            MenuModel(12, "Pajak & Pembayaran", R.drawable.kt_aspirasi),
            MenuModel(13, "Aspirasi & Pengaduan", R.drawable.kt_aspirasi),
            MenuModel(14, "Aspirasi & Pengaduan", R.drawable.kt_aspirasi)
        )

        val adapter = KategoriGridAdapter(categoryList) { item ->
            Toast.makeText(requireContext(), "Pilih: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        binding.rvKategoriGrid.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}