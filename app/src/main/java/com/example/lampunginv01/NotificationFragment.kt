package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lampunginv01.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        
        // Handle System Bar Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Sesuaikan persis dengan fragment lain (40)
            v.setPadding(v.paddingLeft, systemBars.top + 40, v.paddingRight, v.paddingBottom)
            insets
        }

        setupRecyclerView()
        
        return binding.root
    }

    private fun setupRecyclerView() {
        val notifications = listOf(
            NotificationModel(
                "BPBD Provinsi Lampung",
                "Peringatan Dini Banjir di Sejumlah Wilayah Lampung",
                "25 menit yang lalu",
                R.drawable.bpbd
            )
        )

        val adapter = NotificationAdapter(notifications) { notification ->
            val intent = Intent(requireContext(), NotificationDetailActivity::class.java)
            startActivity(intent)
        }
        binding.rvNotifications.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}