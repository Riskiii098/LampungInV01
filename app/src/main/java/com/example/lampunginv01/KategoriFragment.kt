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
        // Data Dummy (13 Item: 12 Normal + 1 Spacer + 1 Kanan)
        val categoryList = listOf(
            MenuModel(1, "Aspirasi & Pengaduan", R.drawable.ic_kt1),
            MenuModel(2, "Darurat & Keamanan", R.drawable.ic_kt2),
            MenuModel(3, "Administrasi Kependudukan", R.drawable.ic_kt3),
            MenuModel(4, "Layanan Sosial & Keagamaan", R.drawable.ic_kt4),
            MenuModel(5, "Kesehatan", R.drawable.ic_kt5),
            MenuModel(6, "Informasi Publik & Data", R.drawable.ic_kt6),
            MenuModel(7, "Fasilitas Umum", R.drawable.ic_kt7),
            MenuModel(8, "Pajak & Pembayaran", R.drawable.ic_kt8),
            MenuModel(9, "Pendidikan & Pengembangan SDM", R.drawable.ic_kt9),
            MenuModel(10, "Perizinan & Usaha", R.drawable.ic_kt10),
            MenuModel(11, "Rekreasi", R.drawable.ic_kt11),
            MenuModel(12, "Lingkungan & Kebencanaan", R.drawable.ic_kt12),
            MenuModel(-1, "", 0), // Item Spacer agar item selanjutnya di kanan
            MenuModel(13, "Ketenagakerjaan", R.drawable.ic_kt13)
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