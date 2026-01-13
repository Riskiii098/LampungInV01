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
    private var currentTab = 0 

    companion object {
        const val TAB_LAPORAN = 0
        const val TAB_DISIMPAN = 1
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

        setupTabs()
        setupListeners()
        switchTab(TAB_LAPORAN) // Default tab
    }

    private fun setupListeners() {
        binding.layoutLaporanContent.cardCekLaporan.setOnClickListener {
            val intent = Intent(requireContext(), RiwayatLaporanActivity::class.java)
            startActivity(intent)
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
            binding.tvDisimpanPlaceholder.visibility = View.GONE

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
            binding.tvDisimpanPlaceholder.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}