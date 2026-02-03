package com.example.lampunginv01

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lampunginv01.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {
    private var _binding: FragmentActivityBinding? = null
    private val binding get() = _binding!!

    // State tab saat ini
    private var currentTab = TAB_LAPORAN
    private var currentDisimpanSubTab = SUB_TAB_BERITA

    companion object {
        const val TAB_LAPORAN = 0
        const val TAB_DISIMPAN = 1
        
        const val SUB_TAB_BERITA = 0
        const val SUB_TAB_LAPORAN = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Handle System Bar Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top + 40, v.paddingRight, v.paddingBottom)
            insets
        }

        // Cek apakah ada request simulasi data
        val simulateData = arguments?.getBoolean("SIMULATE_DATA", false) ?: false
        if (simulateData) {
            // Update Angka Statistik
            binding.layoutLaporanContent.tvStatTotalDibuat.text = "1"
            binding.layoutLaporanContent.tvStatusMenungguCount.text = "1"

            // Tampilkan Kartu Laporan (Menunggu Verifikasi)
            binding.layoutLaporanContent.tvEmptyLaporan.visibility = View.GONE
            binding.layoutLaporanContent.layoutFilledLaporan.visibility = View.VISIBLE
        } else {
            // Reset ke Default (Kosong) jika tidak ada simulasi
            binding.layoutLaporanContent.tvStatTotalDibuat.text = "0"
            binding.layoutLaporanContent.tvStatusMenungguCount.text = "0"
            binding.layoutLaporanContent.tvEmptyLaporan.visibility = View.VISIBLE
            binding.layoutLaporanContent.layoutFilledLaporan.visibility = View.GONE
        }

        setupTabs()
        setupListeners()
        switchTab(TAB_LAPORAN) // Default tab
    }

    private fun setupListeners() {
        binding.layoutLaporanContent.cardCekLaporan.setOnClickListener {
            val countText = binding.layoutLaporanContent.tvStatTotalDibuat.text.toString()
            val count = countText.toIntOrNull() ?: 0
            
            val intent = if (count > 0) {
                Intent(requireContext(), RiwayatLaporanActivity::class.java)
            } else {
                Intent(requireContext(), EmptyHistoryActivity::class.java)
            }
            startActivity(intent)
        }

        binding.btnFilterBerita.setOnClickListener {
            switchDisimpanSubTab(SUB_TAB_BERITA)
        }

        binding.btnFilterLaporan.setOnClickListener {
            switchDisimpanSubTab(SUB_TAB_LAPORAN)
        }

        binding.btnBacaBerita.setOnClickListener {
            startActivity(Intent(requireContext(), BeritaActivity::class.java))
        }

        binding.btnLihatLaporanLainnya.setOnClickListener {
            startActivity(Intent(requireContext(), CitizenReportActivity::class.java))
        }
    }

    private fun setupTabs() {
        binding.tabLaporanSaya.setOnClickListener {
            switchTab(TAB_LAPORAN)
        }

        binding.tabDisimpan.setOnClickListener {
            switchTab(TAB_DISIMPAN)
        }
    }

    private fun switchTab(tabIndex: Int) {
        currentTab = tabIndex
        val activeColor = Color.BLACK
        val inactiveColor = Color.DKGRAY

        if (tabIndex == TAB_LAPORAN) {
            // UI Tab Laporan Aktif
            binding.tvTabLaporan.setTextColor(activeColor)
            binding.tvTabLaporan.setTypeface(null, Typeface.BOLD)
            binding.indicatorLaporan.visibility = View.VISIBLE

            // UI Tab Disimpan Inaktif
            binding.tvTabDisimpan.setTextColor(inactiveColor)
            binding.tvTabDisimpan.setTypeface(null, Typeface.NORMAL)
            binding.indicatorDisimpan.visibility = View.INVISIBLE
            
            // Ubah konten
            binding.layoutLaporanContent.root.visibility = View.VISIBLE
            binding.layoutDisimpanContent.visibility = View.GONE

        } else {
            // UI Tab Laporan Inaktif
            binding.tvTabLaporan.setTextColor(inactiveColor)
            binding.tvTabLaporan.setTypeface(null, Typeface.NORMAL)
            binding.indicatorLaporan.visibility = View.INVISIBLE

            // UI Tab Disimpan Aktif
            binding.tvTabDisimpan.setTextColor(activeColor)
            binding.tvTabDisimpan.setTypeface(null, Typeface.BOLD)
            binding.indicatorDisimpan.visibility = View.VISIBLE
            
            // Ubah konten
            binding.layoutLaporanContent.root.visibility = View.GONE
            binding.layoutDisimpanContent.visibility = View.VISIBLE
            
            // Pastikan subtab default benar saat pertama kali dibuka
            switchDisimpanSubTab(currentDisimpanSubTab)
        }
    }

    private fun switchDisimpanSubTab(subTabIndex: Int) {
        currentDisimpanSubTab = subTabIndex
        
        // Safety check to ensure the views are available and we are on the right tab
        if (currentTab != TAB_DISIMPAN) return
        
        if (subTabIndex == SUB_TAB_BERITA) {
            // Berita Aktif
            binding.btnFilterBerita.setBackgroundResource(R.drawable.bg_black_rounded_50)
            binding.btnFilterBerita.setTextColor(Color.WHITE)
            
            // Laporan Inaktif
            binding.btnFilterLaporan.setBackgroundResource(R.drawable.bg_gray_rounded_50)
            binding.btnFilterLaporan.setTextColor(Color.BLACK)
            
            // Tampilan Konten
            binding.layoutEmptyBerita.visibility = View.VISIBLE
            binding.layoutEmptyLaporanWarga.visibility = View.GONE
        } else {
            // Berita Inaktif
            binding.btnFilterBerita.setBackgroundResource(R.drawable.bg_gray_rounded_50)
            binding.btnFilterBerita.setTextColor(Color.BLACK)
            
            // Laporan Aktif
            binding.btnFilterLaporan.setBackgroundResource(R.drawable.bg_black_rounded_50)
            binding.btnFilterLaporan.setTextColor(Color.WHITE)
            
            // Tampilan Konten
            binding.layoutEmptyBerita.visibility = View.GONE
            binding.layoutEmptyLaporanWarga.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}