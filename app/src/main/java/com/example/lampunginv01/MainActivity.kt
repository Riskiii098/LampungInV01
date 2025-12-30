package com.example.lampunginv01

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lampunginv01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Set padding hanya untuk bawah (navigasi) agar bottom nav naik
            // Biarkan top (status bar) handled oleh Fragment masing-masing atau background activity
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        setupBottomNavigation()

        // Tampilkan HomeFragment saat pertama kali dibuka
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            updateBottomNavUI(0)
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavInclude.navHome.setOnClickListener {
            loadFragment(HomeFragment())
            updateBottomNavUI(0)
        }

        binding.bottomNavInclude.navActivity.setOnClickListener {
            loadFragment(ActivityFragment())
            updateBottomNavUI(1)
        }

        binding.bottomNavInclude.navReport.setOnClickListener {
            loadFragment(ReportFragment())
            updateBottomNavUI(2)
        }

        binding.bottomNavInclude.navNotification.setOnClickListener {
            loadFragment(NotificationFragment())
            updateBottomNavUI(3)
        }

        binding.bottomNavInclude.navProfile.setOnClickListener {
            loadFragment(ProfileFragment())
            updateBottomNavUI(4)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    private fun updateBottomNavUI(index: Int) {
        // Warna Default (Gray) dan Selected (Black)
        val colorDefault = Color.parseColor("#808080")
        val colorSelected = Color.BLACK

        // Reset state
        binding.bottomNavInclude.ivNavHome.imageTintList = ColorStateList.valueOf(colorDefault)
        binding.bottomNavInclude.tvNavHome.setTextColor(colorDefault)
        
        binding.bottomNavInclude.ivNavActivity.imageTintList = ColorStateList.valueOf(colorDefault)
        binding.bottomNavInclude.tvNavActivity.setTextColor(colorDefault)
        
        // Report icon (ic_lapor) tidak di-tint agar tetap original
        binding.bottomNavInclude.tvNavReport.setTextColor(colorDefault)
        
        binding.bottomNavInclude.ivNavNotification.imageTintList = ColorStateList.valueOf(colorDefault)
        binding.bottomNavInclude.tvNavNotification.setTextColor(colorDefault)
        
        binding.bottomNavInclude.ivNavProfile.imageTintList = ColorStateList.valueOf(colorDefault)
        binding.bottomNavInclude.tvNavProfile.setTextColor(colorDefault)

        // Set selected state
        when (index) {
            0 -> {
                binding.bottomNavInclude.ivNavHome.imageTintList = ColorStateList.valueOf(colorSelected)
                binding.bottomNavInclude.tvNavHome.setTextColor(colorSelected)
            }
            1 -> {
                binding.bottomNavInclude.ivNavActivity.imageTintList = ColorStateList.valueOf(colorSelected)
                binding.bottomNavInclude.tvNavActivity.setTextColor(colorSelected)
            }
            2 -> {
                binding.bottomNavInclude.tvNavReport.setTextColor(colorSelected)
            }
            3 -> {
                binding.bottomNavInclude.ivNavNotification.imageTintList = ColorStateList.valueOf(colorSelected)
                binding.bottomNavInclude.tvNavNotification.setTextColor(colorSelected)
            }
            4 -> {
                binding.bottomNavInclude.ivNavProfile.imageTintList = ColorStateList.valueOf(colorSelected)
                binding.bottomNavInclude.tvNavProfile.setTextColor(colorSelected)
            }
        }
    }
}