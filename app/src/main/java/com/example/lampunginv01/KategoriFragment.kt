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
        // Data 11 Kategori Baru
        val categoryList = listOf(
            MenuModel(1, "Aspirasi dan Pengaduan", R.drawable.ic_kt1),
            MenuModel(2, "Darurat dan Keamanan", R.drawable.ic_kt2),
            MenuModel(3, "Kependudukan", R.drawable.ic_kt3),
            MenuModel(4, "Layanan Sosial & Keagamaan", R.drawable.ic_kt4),
            MenuModel(5, "Kesehatan", R.drawable.ic_kt5),
            MenuModel(6, "Informasi Publik & Data", R.drawable.ic_kt6),
            MenuModel(7, "Info Lokasi", R.drawable.ic_kt7),
            MenuModel(8, "Perekonomian", R.drawable.ic_kt8),
            MenuModel(9, "Pendidikan dan Pengembangan SDM", R.drawable.ic_kt9),
            MenuModel(10, "Karir dan Usaha", R.drawable.shop),
            MenuModel(-1, "", 0), // Spacer agar item ke-11 pindah ke kanan
            MenuModel(11, "Lingkungan", R.drawable.ic_kt12)
        )

        val adapter = KategoriGridAdapter(categoryList) { item ->
            if (item.title.contains("Aspirasi")) {
                val intent = android.content.Intent(requireContext(), AspirasiActivity::class.java)
                startActivity(intent)
            } else if (item.title.contains("Darurat")) {
                val intent = android.content.Intent(requireContext(), DaruratActivity::class.java)
                startActivity(intent)
            } else if (item.title == "Kesehatan") {
                val intent = android.content.Intent(requireContext(), KesehatanActivity::class.java)
                startActivity(intent)
            } else if (item.title == "Kependudukan") {
                val intent = android.content.Intent(requireContext(), KependudukanActivity::class.java)
                startActivity(intent)
            } else if (item.title == "Info Lokasi") {
                val intent = android.content.Intent(requireContext(), InfoLokasiActivity::class.java)
                startActivity(intent)
            } else if (item.title.contains("Pendidikan")) {
                val intent = android.content.Intent(requireContext(), PendidikanActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Pilih: ${item.title}", Toast.LENGTH_SHORT).show()
            }
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